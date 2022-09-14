package com.kbcs.data.repository

import com.kbcs.data.service.StoreService
import com.kbsc.data.dto.DiscountStoreDetailDto
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeService: StoreService
) : StoreRepository {
    override suspend fun getStoreDetailList(storeId: String): Result<DiscountStoreDetailDto> {
        return runCatching { storeService.getStoreDetailList(storeId) }
    }
}
