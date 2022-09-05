package com.kbcs.soptionssix.buy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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

    fun setFoodCount(degree: Int) {
        if (_uiState.value.foodCount + degree > -1) {
            _uiState.value = _uiState.value.copy(foodCount = _uiState.value.foodCount + degree)
        }
    }

    fun writePhoneNumber(text: String) {
        _uiState.value = _uiState.value.copy(userPhoneNumber = text)
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
    val storeArrivalTime: Int = -1,
    val isChallenge: Boolean = true,
    val payment: Int = -1
)
