package com.kbcs.soptionssix.di

import com.kbcs.data.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideDiscountService(retrofit: Retrofit): DiscountService =
        retrofit.create(DiscountService::class.java)

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

    @Singleton
    @Provides
    fun provideReceiptService(retrofit: Retrofit): ReceiptService =
        retrofit.create(ReceiptService::class.java)

    @Singleton
    @Provides
    fun provideReviewService(retrofit: Retrofit): ReviewService =
        retrofit.create(ReviewService::class.java)

    @Singleton
    @Provides
    fun provideStoreService(retrofit: Retrofit): StoreService =
        retrofit.create(StoreService::class.java)

    @Singleton
    @Provides
    fun provideExchangeService(retrofit: Retrofit): ExchangeService =
        retrofit.create(ExchangeService::class.java)
}
