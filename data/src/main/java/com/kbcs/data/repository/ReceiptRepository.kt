package com.kbcs.data.repository

import com.kbsc.data.dto.ReceiptDto
import com.kbsc.data.entity.request.ReceiptRequest

interface ReceiptRepository {
    suspend fun getReceiptList(): Result<List<ReceiptDto>>
    suspend fun postReceipt(receiptRequest: ReceiptRequest)
}
