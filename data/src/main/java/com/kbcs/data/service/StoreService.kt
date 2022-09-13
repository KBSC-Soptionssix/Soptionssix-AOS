package com.kbcs.data.service

import com.kbsc.data.dto.StoreDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreService {
    @GET("/store/{id}")
    suspend fun getStoreContent(
        @Path("id") id: String
    ): StoreDetailDto
}
