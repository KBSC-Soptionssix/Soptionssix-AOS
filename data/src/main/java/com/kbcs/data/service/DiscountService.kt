package com.kbcs.data.service

import com.kbsc.data.dto.DiscountDto
import retrofit2.http.GET

interface DiscountService {
    @GET("/discount")
    suspend fun getDiscountList(): DiscountDto
}
