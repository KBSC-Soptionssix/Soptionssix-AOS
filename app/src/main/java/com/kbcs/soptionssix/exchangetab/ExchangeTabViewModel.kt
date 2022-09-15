package com.kbcs.soptionssix.exchangetab

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcs.data.repository.ReceiptRepository
import com.kbsc.data.dto.ReviewDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeTabViewModel @Inject constructor(
    private val receiptRepository: ReceiptRepository
) : ViewModel() {
    private val _receiptList: MutableStateFlow<List<Receipt>> = MutableStateFlow(emptyList())
    val receiptList = _receiptList.asStateFlow()

    init {
        fetchReceiptList()
    }

    fun fetchReceiptList() {
        viewModelScope.launch {
            receiptRepository.getReceiptList()
                .onSuccess { resultReceiptList ->
                    _receiptList.value = resultReceiptList.map { receiptDto ->
                        Receipt(
                            id = receiptDto.id,
                            storeId = receiptDto.store.id,
                            userId = receiptDto.userId,
                            storeName = receiptDto.store.name,
                            productName = receiptDto.product.name,
                            productCount = receiptDto.productCount,
                            totalProductPrice = receiptDto.productCount * receiptDto.product.price,
                            exchangeDate = receiptDto.date,
                            pickUpDate = receiptDto.pickUpTime,
                            address = receiptDto.store.address,
                            loadAddress = receiptDto.store.loadAddress,
                            review = receiptDto.review
                        )
                    }
                }
                .onFailure { Log.d("ReceiptViewModel", "error: ${it.message}") }
        }
    }
}

data class Receipt(
    val id: String,
    val storeId: String,
    val userId: String,
    val storeName: String,
    val address: String,
    val loadAddress: String,
    val productName: String,
    val totalProductPrice: Int,
    val productCount: Int,
    val exchangeDate: Long,
    val pickUpDate: Long,
    val review: ReviewDto? = null
)
