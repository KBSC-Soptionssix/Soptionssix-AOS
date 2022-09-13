package com.kbcs.data.repository

import com.kbsc.data.dto.StoreDetailDto

interface StoreRepository {
    suspend fun getStoreContent(id: String): Result<StoreDetailDto>
}
