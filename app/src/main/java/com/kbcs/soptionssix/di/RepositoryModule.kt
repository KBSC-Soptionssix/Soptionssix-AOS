package com.kbcs.soptionssix.di

import com.kbcs.data.repository.DiscountRepository
import com.kbcs.data.repository.DiscountRepositoryImpl
import com.kbcs.data.repository.ProductRepository
import com.kbcs.data.repository.ProductRepositoryImpl
import com.kbcs.data.repository.ReceiptRepository
import com.kbcs.data.repository.ReceiptRepositoryImpl
import com.kbcs.data.repository.ReviewRepository
import com.kbcs.data.repository.ReviewRepositoryImpl
import com.kbcs.data.repository.StoreRepository
import com.kbcs.data.repository.StoreRepositoryImpl
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
}
