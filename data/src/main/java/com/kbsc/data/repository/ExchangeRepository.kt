package com.kbsc.data.repository

import com.kbsc.data.dto.ReceiptDto

interface ExchangeRepository {
    suspend fun getExchangeDetailList(id: String): Result<ReceiptDto>
}
