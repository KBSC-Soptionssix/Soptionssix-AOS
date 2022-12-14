import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    compileSdk = Constants.compileSdk

    defaultConfig {
        applicationId = Constants.packageName
        minSdk = Constants.minSdk
        targetSdk = Constants.targetSdk
        versionCode = Constants.versionCode
        versionName = Constants.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "NAVER_KEY", properties.getProperty("NAVER_KEY"))
        manifestPlaceholders["NAVER_KEY"] = properties.getProperty("NAVER_KEY")
        buildConfigField("String", "INVISIBLE_GUEST_TOKEN", properties.getProperty("INVISIBLE_GUEST_TOKEN"))
        buildConfigField("String", "INVISIBLE_GUEST_URL", properties.getProperty("INVISIBLE_GUEST_URL"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
    lint {
        baseline = file("lint-baseline.xml")
        disable.add("JvmStaticProvidesInObjectDetector")
        disable.add("FieldSiteTargetOnQualifierAnnotation")
        disable.add("ModuleCompanionObjects")
        disable.add("ModuleCompanionObjectsNotInModuleParent")
    }
}

dependencies {
    implementation(project(":data"))
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    hilt()
    compose()
    network()
    Dependencies.run {
        implementation(naverMap)
        implementation(naverAppCompat)
        implementation(naverMap) {
            exclude(group = "com.android.support")
        }
        implementation(naverLocation)
        implementation(kotlin)
        implementation(coroutine)
        implementation(coreKTX)
        implementation(activityKTX)
        implementation(fragmentKTX)
        implementation(lifecycleKTX)
        implementation(appCompat)
        implementation(material)
        implementation(constraintLayout)
        implementation(coil)
        implementation(coilCompose)
        implementation(pager)
        implementation(recyclerView)
        implementation(navigation)

        testImplementation(jUnit)
        androidTestImplementation(androidTest)
        androidTestImplementation(espresso)
    }
}

ktlint {
    android.set(true)
    coloredOutput.set(true)
    verbose.set(true)
    outputToConsole.set(true)
    disabledRules.set(setOf("max-line-length", "no-wildcard-imports", "import-ordering"))
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}
