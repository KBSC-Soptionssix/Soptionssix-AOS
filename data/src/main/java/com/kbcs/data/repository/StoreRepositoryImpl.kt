package com.kbcs.data.repository

import com.kbcs.data.service.StoreService
import com.kbsc.data.dto.StoreDetailDto
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeService: StoreService
) : StoreRepository {
    override suspend fun getStoreContent(id: String): Result<StoreDetailDto> {
        return runCatching { storeService.getStoreContent(id) }
    }
}
