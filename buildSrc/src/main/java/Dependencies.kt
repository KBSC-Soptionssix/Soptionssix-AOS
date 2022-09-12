import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    // kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}"

    // androidx
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"

    const val hiltClassPath = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltKapt = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    const val coreKTX = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val activityKTX = "androidx.activity:activity-ktx:${Versions.activityKTXVersion}"
    const val lifecycleKTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKTXVersion}"
    const val fragmentKTX = "androidx.fragment:fragment-ktx:${Versions.fragmentKTXVersion}"
    const val pager = "com.google.accompanist:accompanist-pager:${Versions.pager}"

    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    const val composeAnimation = "androidx.compose.animation:animation:${Versions.composeVersion}"
    const val composeUi = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val composeViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModelVersion}"
    const val composeTheme =
        "com.google.accompanist:accompanist-appcompat-theme:${Versions.composeThemeVersion}"
    const val composeAdapter =
        "com.google.android.material:compose-theme-adapter:${Versions.composeAdapterVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"

    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"

    // test
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val androidTest = "androidx.test.ext:junit:${Versions.androidTestVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

    // Third Party
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilVersion}"
    const val interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptorVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
    const val retrofit2Converter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit2Version}"

    // NAVER MAP
    const val naverMap = "com.naver.maps:map-sdk:3.15.0"
    const val naverLocation = "com.google.android.gms:play-services-location:20.0.0"
    const val naverAppCompat = "com.android.support:appcompat-v7:28.0.0"

    const val navigation = "androidx.navigation:navigation-compose:${Versions.navVersion}"
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltKapt)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeAnimation)
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeTheme)
    implementation(Dependencies.composeAdapter)
    implementation(Dependencies.composeMaterial)
}

fun DependencyHandler.network() {
    implementation(Dependencies.gson)
    implementation(Dependencies.retrofit2)
    implementation(Dependencies.retrofit2Converter)
    implementation(Dependencies.interceptor)
}

private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}
