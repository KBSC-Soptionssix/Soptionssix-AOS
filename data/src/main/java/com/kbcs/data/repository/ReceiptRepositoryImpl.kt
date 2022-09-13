package com.kbcs.data.repository

import com.kbcs.data.service.ReceiptService
import com.kbsc.data.dto.ReceiptDto
import javax.inject.Inject

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptService: ReceiptService
) : ReceiptRepository {
    override suspend fun getReceiptList(): Result<List<ReceiptDto>> {
        return runCatching { receiptService.getReceiptList() }
    }
}
