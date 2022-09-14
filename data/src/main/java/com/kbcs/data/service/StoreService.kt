package com.kbcs.data.service

import com.kbsc.data.dto.DiscountStoreDetailDto
import com.kbsc.data.dto.StoreDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreService {
    @GET("/discount/{storeId}")
    suspend fun getStoreDetailList(
        @Path("storeId") id: String
    ): DiscountStoreDetailDto

    @GET("/store/{id}")
    suspend fun getStoreContent(
        @Path("id") id: String
    ): StoreDetailDto

}
