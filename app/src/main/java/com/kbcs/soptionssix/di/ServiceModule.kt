package com.kbcs.soptionssix.di

import com.kbcs.data.service.DiscountService
import com.kbcs.data.service.ProductService
import com.kbcs.data.service.ReceiptService
import com.kbcs.data.service.ReviewService
import com.kbcs.data.service.StoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create

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
}
