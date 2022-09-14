package com.kbcs.data.repository

import com.kbsc.data.dto.DiscountStoreDetailDto

interface StoreRepository {
    suspend fun getStoreDetailList(storeId: String): Result<DiscountStoreDetailDto>
}
