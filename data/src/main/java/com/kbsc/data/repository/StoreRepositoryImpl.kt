package com.kbsc.data.repository

import com.kbsc.data.service.StoreService
import com.kbsc.data.dto.DiscountStoreDetailDto
import com.kbsc.data.dto.StoreDetailDto
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeService: StoreService
) : StoreRepository {
    override suspend fun getStoreDetailList(storeId: String): Result<DiscountStoreDetailDto> {
        return runCatching { storeService.getStoreDetailList(storeId) }
    }

    override suspend fun getStoreContent(id: String): Result<StoreDetailDto> {
        return runCatching { storeService.getStoreContent(id) }
    }
}
