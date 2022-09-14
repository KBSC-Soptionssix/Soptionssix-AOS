package com.kbcs.soptionssix.store

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kbcs.data.repository.StoreRepository
import com.kbsc.data.dto.DiscountStoreDetailDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {
    private var _storeInfo = MutableLiveData<DiscountStoreDetailDto>()
    val storeInfo: LiveData<DiscountStoreDetailDto> get() = _storeInfo

    suspend fun fetchStoreDetailList(storeId: String) {
        storeRepository.getStoreDetailList(storeId)
            .onSuccess { DiscountStoreDetailDto ->
                _storeInfo.value = DiscountStoreDetailDto
            }
            .onFailure {
                Log.d("StoreViewModel", "error: ${it.message}")
            }
    }
}
