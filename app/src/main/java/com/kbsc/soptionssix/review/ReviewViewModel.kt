package com.kbsc.soptionssix.review

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbsc.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    private val _reviewList: MutableStateFlow<List<Review>> = MutableStateFlow(emptyList())
    val reviewList = _reviewList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchReviewList()
        }
    }

    suspend fun fetchReviewList() {
        reviewRepository.getReviewList()
            .onSuccess { resultReviewList ->
                val currentTime = Calendar.getInstance().timeInMillis / 1000L
                _reviewList.value = resultReviewList.map { reviewDto ->
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
            .onFailure { Log.d("ReviewViewModel", "error: ${it.message}") }
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
