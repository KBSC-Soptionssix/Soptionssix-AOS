package com.kbcs.soptionssix.di

import com.kbcs.data.repository.*
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
