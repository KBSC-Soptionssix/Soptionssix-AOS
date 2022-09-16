package com.kbsc.data.repository

import com.kbsc.data.service.DiscountService
import com.kbsc.data.dto.DiscountDto
import javax.inject.Inject

class DiscountRepositoryImpl @Inject constructor(
    private val discountService: DiscountService
) : DiscountRepository {
    override suspend fun getDiscountList(): Result<DiscountDto> {
        return runCatching { discountService.getDiscountList() }
    }
}
