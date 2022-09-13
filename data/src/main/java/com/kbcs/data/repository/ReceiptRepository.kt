package com.kbcs.data.repository

import com.kbsc.data.dto.ReceiptDto

interface ReceiptRepository {
    suspend fun getReceiptList(): Result<List<ReceiptDto>>
}
