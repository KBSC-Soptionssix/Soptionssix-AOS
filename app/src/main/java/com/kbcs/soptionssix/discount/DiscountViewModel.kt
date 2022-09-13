package com.kbcs.soptionssix.discount

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcs.data.repository.DiscountRepository
import com.kbsc.data.dto.ProductDto
import com.kbsc.data.dto.StoreDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscountViewModel @Inject constructor(
    private val discountRepository: DiscountRepository
) : ViewModel() {
    private var _storeList = MutableLiveData<List<StoreDto>>()
    val storeList: LiveData<List<StoreDto>> get() = _storeList

    private var _productList = MutableLiveData<List<ProductDto>>()
    val productList: LiveData<List<ProductDto>> get() = _productList

    private var _waitingList = MutableLiveData<List<ProductDto>>()
    val waitingList: LiveData<List<ProductDto>> get() = _waitingList

    init {
        viewModelScope.launch {
            fetchDiscountList()
        }
    }

    suspend fun fetchDiscountList() {
        discountRepository.getDiscountList()
            .onSuccess { DiscountDto ->
                _storeList.value = DiscountDto.storeList
                _productList.value = DiscountDto.productList
                _waitingList.value = DiscountDto.waitDonationList
            }
            .onFailure {
                Log.d("DiscountViewModel", "error: ${it.message}")
            }
    }
}
