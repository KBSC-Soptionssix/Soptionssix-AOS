package com.kbcs.data.repository

import com.kbsc.data.dto.DiscountDto

interface DiscountRepository {
    suspend fun getDiscountList(): Result<DiscountDto>
}
