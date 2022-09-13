package com.kbcs.data.service

import com.kbsc.data.dto.ReceiptDto
import retrofit2.http.GET

interface ReceiptService {
    @GET("/receipt")
    suspend fun getReceiptList(): List<ReceiptDto>
}
