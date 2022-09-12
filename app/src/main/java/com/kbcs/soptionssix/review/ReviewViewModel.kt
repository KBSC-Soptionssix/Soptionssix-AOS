package com.kbcs.soptionssix.review

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbsc.data.dto.ProductDto
import com.kbsc.data.dto.ReceiptPreviewDto
import com.kbsc.data.dto.ReviewDto
import com.kbsc.data.dto.StorePreviewDto
import com.kbsc.data.dto.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor() : ViewModel() {
    private val _reviewList: MutableStateFlow<List<Review>> = MutableStateFlow(emptyList())
    val reviewList = _reviewList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchReviewList()
        }
    }

    suspend fun fetchReviewList() {
        val tempList = listOf(
            ReviewDto(
                id = "1",
                user = UserDto("1", "맑은비", "01012345678"),
                receiptPreview =
                ReceiptPreviewDto(
                    id = "",
                    userId = "",
                    product = ProductDto(
                        id = "",
                        storePreview =
                        StorePreviewDto(
                            id = "1",
                            photo = "",
                            name = "몽실 베이커리",
                            category = "",
                            description = "",
                            maxDiscount = 4500,
                            discountStartTime = null,
                            phone = "01012345678",
                            breakStartTime = null,
                            breakEndTime = null,
                            startTime = 0,
                            endTime = 0,
                            hasChallenge = true
                        ),
                        photo = "",
                        name = "",
                        stockCount = 1,
                        price = 9000,
                        discount = 4500,
                        donationCompleteCount = 0,
                        donationWaitCount = 0
                    ),
                    productCount = 1,
                    date = 0L,
                    pickUpTime = 1662159018,
                    paymentMethod = "",
                    isChallenge = true,
                    isDonate = true
                ),
                region = "백현동",
                content = "첫번째 방문에 너무 득템했는데\n" +
                    "오늘 두번쨰 방문인데 후기를 안남길 수가 없어요\n" +
                    "참깨, 무화과, 갈릭바게트 베이글 전부 다 존맛이고 크림치즈 서비스스스스스스\n" +
                    "이거까지 보이나요???",
                photos = emptyList(),
                createdAt = 1662193230
            ),
            ReviewDto(
                id = "1",
                user = UserDto("1", "맑은비", "01012345678"),
                receiptPreview =
                ReceiptPreviewDto(
                    id = "",
                    userId = "",
                    product = ProductDto(
                        id = "",
                        storePreview =
                        StorePreviewDto(
                            id = "1",
                            photo = "",
                            name = "몽실 베이커리",
                            category = "",
                            description = "",
                            maxDiscount = 4500,
                            discountStartTime = null,
                            phone = "01012345678",
                            breakStartTime = null,
                            breakEndTime = null,
                            startTime = 0,
                            endTime = 0,
                            hasChallenge = true
                        ),
                        photo = "",
                        name = "",
                        stockCount = 1,
                        price = 9000,
                        discount = 4500,
                        donationCompleteCount = 0,
                        donationWaitCount = 0
                    ),
                    productCount = 1,
                    date = 0L,
                    pickUpTime = 1662159018,
                    paymentMethod = "",
                    isChallenge = true,
                    isDonate = true
                ),
                region = "백현동",
                content = "첫번째 방문에 너무 득템했는데\n" +
                    "오늘 두번쨰 방문인데 후기를 안남길 수가 없어요\n" +
                    "참깨, 무화과, 갈릭바게트 베이글 전부 다 존맛이고 크림치즈 서비스스스스스스\n" +
                    "이거까지 보이나요???",
                photos = listOf("https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800"),
                createdAt = 1662159018
            ),
            ReviewDto(
                id = "1",
                user = UserDto("1", "맑은비", "01012345678"),
                receiptPreview =
                ReceiptPreviewDto(
                    id = "",
                    userId = "",
                    product = ProductDto(
                        id = "",
                        storePreview =
                        StorePreviewDto(
                            id = "1",
                            photo = "",
                            name = "몽실 베이커리",
                            category = "",
                            description = "",
                            maxDiscount = 4500,
                            discountStartTime = null,
                            phone = "01012345678",
                            breakStartTime = null,
                            breakEndTime = null,
                            startTime = 0,
                            endTime = 0,
                            hasChallenge = true
                        ),
                        photo = "",
                        name = "",
                        stockCount = 1,
                        price = 9000,
                        discount = 4500,
                        donationCompleteCount = 0,
                        donationWaitCount = 0
                    ),
                    productCount = 1,
                    date = 0L,
                    pickUpTime = 1662159018,
                    paymentMethod = "",
                    isChallenge = true,
                    isDonate = true
                ),
                region = "백현동",
                content = "첫번째 방문에 너무 득템했는데\n" +
                    "오늘 두번쨰 방문인데 후기를 안남길 수가 없어요\n" +
                    "참깨, 무화과, 갈릭바게트 베이글 전부 다 존맛이고 크림치즈 서비스스스스스스\n" +
                    "이거까지 보이나요???",
                photos = listOf(
                    "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                    "https://d12zq4w4guyljn.cloudfront.net/300_300_20220805020951_menu1_06d69fb0c864.jpg"
                ),
                createdAt = 1662036618
            )
        )
        delay(300)
        val currentTime = Calendar.getInstance().timeInMillis / 1000L
        _reviewList.value = tempList.map { reviewDto ->
            Review(
                storeId = reviewDto.receiptPreview.product.storePreview.id,
                userName = reviewDto.user.nickName.toString(),
                createReviewTime = formatCreateReviewTime(currentTime, reviewDto.createdAt),
                userRegion = reviewDto.region,
                reviewContent = reviewDto.content,
                storeName = reviewDto.receiptPreview.product.storePreview.name,
                photos = reviewDto.photos
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatCreateReviewTime(currentTime: Long, createdAt: Long): String {
        val diffTime = currentTime - createdAt
        return if (diffTime < ONE_HOUR) "${diffTime / ONE_MIN}분 전"
        else if (diffTime < ONE_DAY) "${diffTime / ONE_HOUR}시간 전"
        else {
            val date = Date()
            date.time = createdAt * 1000L
            val formatter = SimpleDateFormat("yyyy.MM.dd")

            formatter.format(date)
        }
    }

    companion object {
        const val ONE_DAY = 86_400
        const val ONE_HOUR = 3_600
        const val ONE_MIN = 60
    }
}

data class Review(
    val storeId: String,
    val userName: String,
    val createReviewTime: String,
    val userRegion: String,
    val reviewContent: String,
    val storeName: String,
    val photos: List<String>?
)
