package com.kbsc.soptionssix.exchange

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kbsc.data.dto.ReceiptDto
import com.kbsc.data.repository.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeDetailViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) : ViewModel() {
    private var _exchangeList = MutableLiveData<ReceiptDto>()
    val exchangeList: LiveData<ReceiptDto> get() = _exchangeList

    suspend fun fetchExchangeDetailList(id: String) {
        exchangeRepository.getExchangeDetailList(id)
            .onSuccess { ReceiptDto ->
                _exchangeList.value = ReceiptDto
            }
            .onFailure {
                Log.d("ExchangeDetailViewModel", "error: ${it.message}")
            }
    }
}

data class Exchange(
    val id: String,
    val storeName: String,
    val roadAddress: String,
    val address: String,
    val productName: String,
    val productCount: Int
)
