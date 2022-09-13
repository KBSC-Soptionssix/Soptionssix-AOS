package com.kbcs.data.service

import com.kbsc.data.dto.ReceiptDto
import com.kbsc.data.entity.request.ReceiptRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReceiptService {
    @GET("/receipt")
    suspend fun getReceiptList(): List<ReceiptDto>

    @POST("receipt")
    suspend fun postReceipt(
        @Body body: ReceiptRequest
    )
}
