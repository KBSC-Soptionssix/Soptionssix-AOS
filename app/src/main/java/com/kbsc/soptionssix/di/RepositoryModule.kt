package com.kbsc.soptionssix.di

import com.kbsc.data.repository.DiscountRepository
import com.kbsc.data.repository.DiscountRepositoryImpl
import com.kbsc.data.repository.ExchangeRepository
import com.kbsc.data.repository.ExchangeRepositoryImpl
import com.kbsc.data.repository.ProductRepository
import com.kbsc.data.repository.ProductRepositoryImpl
import com.kbsc.data.repository.ReceiptRepository
import com.kbsc.data.repository.ReceiptRepositoryImpl
import com.kbsc.data.repository.ReviewRepository
import com.kbsc.data.repository.ReviewRepositoryImpl
import com.kbsc.data.repository.StoreRepository
import com.kbsc.data.repository.StoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesDiscountRepository(
        impl: DiscountRepositoryImpl
    ): DiscountRepository = impl

    @Provides
    @Singleton
    fun providesProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository = impl

    @Provides
    @Singleton
    fun providesReceiptRepository(
        impl: ReceiptRepositoryImpl
    ): ReceiptRepository = impl

    @Provides
    @Singleton
    fun providesReviewRepository(
        impl: ReviewRepositoryImpl
    ): ReviewRepository = impl

    @Provides
    @Singleton
    fun providesStoreRepository(
        impl: StoreRepositoryImpl
    ): StoreRepository = impl

    @Provides
    @Singleton
    fun provideExchangeRepository(
        impl: ExchangeRepositoryImpl
    ): ExchangeRepository = impl
}
