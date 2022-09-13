package com.kbcs.data.service

import com.kbsc.data.dto.ReceiptDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeService {
    @GET("/receipt/{id}")
    suspend fun getExchangeDetailList(
        @Path("id") id: String
    ): ReceiptDto
}