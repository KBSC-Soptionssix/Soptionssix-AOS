package com.kbsc.data.repository

import com.kbsc.data.service.ExchangeService
import com.kbsc.data.dto.ReceiptDto
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(
    private val exchangeService: ExchangeService
) : ExchangeRepository {
    override suspend fun getExchangeDetailList(id: String): Result<ReceiptDto> {
        return runCatching { exchangeService.getExchangeDetailList(id) }
    }
}
