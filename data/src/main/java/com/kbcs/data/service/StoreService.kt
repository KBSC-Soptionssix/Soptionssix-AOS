package com.kbcs.data.service

import com.kbsc.data.dto.DiscountStoreDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreService {
    @GET("/discount/{storeId}")
    suspend fun getStoreDetailList(
        @Path("storeId") id: String
    ): DiscountStoreDetailDto
}
