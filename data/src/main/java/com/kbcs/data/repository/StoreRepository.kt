package com.kbcs.data.repository

import com.kbsc.data.dto.DiscountStoreDetailDto
import com.kbsc.data.dto.StoreDetailDto

interface StoreRepository {
    suspend fun getStoreDetailList(storeId: String): Result<DiscountStoreDetailDto>
    suspend fun getStoreContent(id: String): Result<StoreDetailDto>
}
