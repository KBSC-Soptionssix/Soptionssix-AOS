package com.kbcs.soptionssix.di

import com.google.gson.GsonBuilder
import com.kbcs.soptionssix.BuildConfig.INVISIBLE_GUEST_TOKEN
import com.kbcs.soptionssix.BuildConfig.INVISIBLE_GUEST_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesInvisibleGuestInterceptor(): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            INVISIBLE_GUEST_TOKEN
                        )
                        .addHeader("Content-Type", "Application/json")
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    fun providesInvisibleGuestOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }
            )
            .build()

    @Provides
    @Singleton
    fun providesInvisibleGuestRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(INVISIBLE_GUEST_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build()
}
