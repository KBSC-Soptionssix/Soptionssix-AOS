package com.kbcs.soptionssix.buy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class BuyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BuyUiState())
    val uiState = _uiState.asStateFlow()
    val totalPrice = _uiState
        .map { uiState -> uiState.foodPrice * uiState.foodCount }
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

    init {
        viewModelScope.launch { fetchNaverMap() }
    }

    suspend fun fetchNaverMap() {
        delay(300)
        _uiState.value = _uiState.value.copy(
            mapX = 36.8198,
            mapY = 127.1544
        )
    }

    fun setFoodCount(degree: Int) {
        if (_uiState.value.foodCount + degree > -1) {
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
}

data class BuyUiState(
    val storeName: String = "멕시칸인더보울 명동점",
    val foodImg: String = "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
    val foodName: String = "상큼 라임 비프 타코 2p",
    val foodCount: Int = 1,
    val foodPrice: Int = 9000,
    val foodDiscount: Int = 50,
    val userPhoneNumber: String = "",
    val loadAddress: String = "",
    val address: String = "",
    val isChallenge: Boolean = true,
    val pickUpTimeList: List<String> = listOf("10분", "20분", "30분", "40분", "50분", "1시간"),
    val pickUpTimeIndex: Int = -1,
    val paymentList: List<String> = listOf("신용/체크카드", "휴대폰결제", "네이버페이", "카카오페이", "토스", "페이코"),
    val selectPaymentIndex: Int = -1,
    val mapX: Double = 37.5005,
    val mapY: Double = 127.0281
)
