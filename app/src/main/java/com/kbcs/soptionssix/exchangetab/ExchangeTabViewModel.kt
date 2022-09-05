package com.kbcs.soptionssix.exchangetab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbsc.data.dto.ProductDto
import com.kbsc.data.dto.ReceiptDto
import com.kbsc.data.dto.ReviewDto
import com.kbsc.data.dto.StoreDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeTabViewModel : ViewModel() {
    private val _receiptList: MutableStateFlow<List<Receipt>> = MutableStateFlow(emptyList())
    val receiptList = _receiptList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchReceiptList()
        }
    }

    suspend fun fetchReceiptList() {
        val tempList = listOf(
            ReceiptDto(
                id = "1",
                userId = "",
                store = StoreDto(
                    name = "멕시칸인더보울 명동점"
                ),
                product = ProductDto(
                    name = "멕시칸 치킨 부리또",
                    price = 4500
                ),
                review = null,
                productCount = 2,
                date = 1662193230,
                pickUpTime = 1663107630,
                paymentMethod = "카카오 페이",
                isChallenge = false,
                isDonate = false
            ),
            ReceiptDto(
                id = "1",
                userId = "",
                store = StoreDto(
                    name = "멕시칸인더보울 명동점"
                ),
                product = ProductDto(
                    name = "멕시칸 치킨 부리또",
                    price = 4500
                ),
                review = null,
                productCount = 2,
                date = 1662193230,
                pickUpTime = 166314,
                paymentMethod = "카카오 페이",
                isChallenge = false,
                isDonate = false
            ),
            ReceiptDto(
                id = "1",
                userId = "",
                store = StoreDto(
                    name = "멕시칸인더보울 명동점"
                ),
                product = ProductDto(
                    name = "멕시칸 치킨 부리또",
                    price = 4500
                ),
                review = ReviewDto(),
                productCount = 2,
                date = 1662193230,
                pickUpTime = 1662294892,
                paymentMethod = "카카오 페이",
                isChallenge = false,
                isDonate = false
            )
        )
        delay(300)
        val tempReceiptList = tempList.map { receiptDto ->
            Receipt(
                id = receiptDto.id,
                userId = receiptDto.userId,
                storeName = receiptDto.store.name,
                productName = receiptDto.product.name,
                productCount = receiptDto.productCount,
                totalProductPrice = receiptDto.productCount * receiptDto.product.price,
                exchangeDate = receiptDto.date,
                pickUpDate = receiptDto.pickUpTime,
                review = receiptDto.review
            )
        }
        _receiptList.value = tempReceiptList
    }
}

data class Receipt(
    val id: String,
    val userId: String,
    val storeName: String,
    val productName: String,
    val totalProductPrice: Int,
    val productCount: Int,
    val exchangeDate: Long,
    val pickUpDate: Long,
    val review: ReviewDto? = null
)
