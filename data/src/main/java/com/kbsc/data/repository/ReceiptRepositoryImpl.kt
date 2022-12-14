package com.kbsc.data.repository

import android.util.Log
import com.kbsc.data.service.ReceiptService
import com.kbsc.data.dto.ReceiptDto
import com.kbsc.data.entity.request.ReceiptRequest
import javax.inject.Inject

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptService: ReceiptService
) : ReceiptRepository {
    override suspend fun getReceiptList(): Result<List<ReceiptDto>> {
        return runCatching { receiptService.getReceiptList() }
    }

    override suspend fun postReceipt(receiptRequest: ReceiptRequest) {
        Log.d("PostReceipt", "request: $receiptRequest")
        runCatching { receiptService.postReceipt(receiptRequest) }
            .onSuccess { Log.d("PostReceipt", "POST 통신 성공") }
            .onFailure { Log.d("PostReceipt", "POST 통신 실패 ${it.message}") }
    }
}
