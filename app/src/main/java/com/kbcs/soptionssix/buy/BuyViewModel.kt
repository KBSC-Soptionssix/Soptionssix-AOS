package com.kbcs.soptionssix.buy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcs.data.repository.ReceiptRepository
import com.kbcs.data.repository.StoreRepository
import com.kbsc.data.dto.StoreDetailDto
import com.kbsc.data.entity.request.ReceiptRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class BuyViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val receiptRepository: ReceiptRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(BuyUiState())
    val uiState = _uiState.asStateFlow()
    val totalPrice = _uiState
        .map { uiState -> (uiState.foodPrice * uiState.foodDiscount) / 100 * uiState.foodCount }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            0
        )
    val buttonState = _uiState.map {
        _uiState.value.userPhoneNumber.isNotEmpty() &&
            (_uiState.value.userPhoneNumber.length == 11) &&
            (_uiState.value.selectPaymentIndex != -1) &&
            (_uiState.value.pickUpTimeIndex != -1) &&
            _uiState.value.foodCount > 0
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        false
    )
    val pickUpTime = _uiState.map { uiState ->
        val addArrivalTime = when (uiState.pickUpTimeIndex) {
            0 -> 10 * 60
            1 -> 20 * 60
            2 -> 30 * 60
            3 -> 40 * 60
            4 -> 50 * 60
            5 -> 60 * 60
            else -> 0
        }
        val currentTime = Calendar.getInstance().timeInMillis / 1000L
        currentTime + addArrivalTime
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        0L
    )

    suspend fun fetchBuyContent(productId: String) {
        storeRepository.getStoreContent(productId)
            .onSuccess { result: StoreDetailDto ->
                _uiState.value = _uiState.value.copy(
                    storeId = result.id,
                    productId = result.product.id,
                    storeName = result.name,
                    foodImg = result.product.photo,
                    foodName = result.product.name,
                    foodDiscount = result.product.discount,
                    foodPrice = result.product.price,
                    foodStockCount = result.product.stockCount,
                    loadAddress = result.loadAddress,
                    address = result.address,
                    mapX = result.mapX.toDouble(),
                    mapY = result.mapY.toDouble()
                )
            }
            .onFailure { Log.d("BuyViewModel", "error: ${it.message}") }
    }

    fun postReceipt() {
        viewModelScope.launch {
            val receiptRequest = ReceiptRequest(
                phone = uiState.value.userPhoneNumber,
                storeId = uiState.value.storeId,
                productId = uiState.value.productId,
                productCount = uiState.value.foodCount,
                pickUpTime = pickUpTime.value,
                paymentMethod = uiState.value.paymentList[uiState.value.selectPaymentIndex],
                isChallenge = uiState.value.isChallenge,
                isDonate = uiState.value.isDonate
            )
            receiptRepository.postReceipt(receiptRequest)
        }
    }

    fun setFoodCount(degree: Int) {
        if (_uiState.value.foodCount + degree > 0 && _uiState.value.foodCount + degree <= _uiState.value.foodStockCount) {
            _uiState.value = _uiState.value.copy(foodCount = _uiState.value.foodCount + degree)
        }
    }

    fun writePhoneNumber(text: String) {
        _uiState.value = _uiState.value.copy(userPhoneNumber = text)
    }

    fun setPickUpTime(index: Int) {
        _uiState.value = _uiState.value.copy(pickUpTimeIndex = index)
    }

    fun setPayment(index: Int) {
        _uiState.value = _uiState.value.copy(selectPaymentIndex = index)
    }

    fun setIsChallenge() {
        _uiState.value = _uiState.value.copy(isChallenge = !_uiState.value.isChallenge)
    }

    fun setIsDonate() {
        _uiState.value = _uiState.value.copy(isDonate = !_uiState.value.isDonate)
        setIsChallenge()
    }
}

data class BuyUiState(
    val storeId: String = "",
    val productId: String = "",
    val storeName: String = "",
    val foodImg: String? = null,
    val foodName: String = "",
    val foodCount: Int = 1,
    val foodStockCount: Int = 1,
    val foodPrice: Int = 0,
    val foodDiscount: Int = 0,
    val userPhoneNumber: String = "",
    val loadAddress: String = "",
    val address: String = "",
    val isChallenge: Boolean = true,
    val isDonate: Boolean = true,
    val pickUpTimeList: List<String> = listOf("10분", "20분", "30분", "40분", "50분", "1시간"),
    val pickUpTimeIndex: Int = -1,
    val paymentList: List<String> = listOf("신용/체크카드", "휴대폰결제", "네이버페이", "카카오페이", "토스", "페이코"),
    val selectPaymentIndex: Int = -1,
    val mapX: Double = 0.0,
    val mapY: Double = 0.0
)
