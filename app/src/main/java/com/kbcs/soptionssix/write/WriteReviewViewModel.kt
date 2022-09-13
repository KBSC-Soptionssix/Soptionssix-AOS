package com.kbcs.soptionssix.write

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcs.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    private val _reviewContent = MutableStateFlow(WriteReviewUiState())
    val reviewContent = _reviewContent.asStateFlow()

    val buttonState = _reviewContent.map {
        reviewContent.value.reviewText.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), false)

    fun fetchReviewContent(id: String) {
        viewModelScope.launch {
            reviewRepository.getReview(id)
                .onSuccess { result ->
                    _reviewContent.value = _reviewContent.value.copy(
                        foodName = result.receiptPreview.product.name,
                        storeName = result.receiptPreview.product.storePreview.name
                    )
                }
                .onFailure { Log.d("WriteReview", "error: ${it.message}") }
        }
    }

    fun writeReview(text: String) {
        _reviewContent.value = _reviewContent.value.copy(reviewText = text)
    }

    fun postReview() {
        /* 통신 작업 */
    }
}

data class WriteReviewUiState(
    val reviewText: String = "",
    val foodName: String = "",
    val storeName: String = ""
)
