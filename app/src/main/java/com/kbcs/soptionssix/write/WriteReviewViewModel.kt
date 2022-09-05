package com.kbcs.soptionssix.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class WriteReviewViewModel @Inject constructor() : ViewModel() {
    private val _reviewContent = MutableStateFlow(WriteReviewUiState())
    val reviewContent = _reviewContent.asStateFlow()

    val buttonState = _reviewContent.map {
        reviewContent.value.reviewText.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), false)

    init {
        viewModelScope.launch { fetchReviewContent() }
    }

    suspend fun fetchReviewContent() {
        _reviewContent.value =
            _reviewContent.value.copy(foodName = "멕시칸인더보울 명동점", storeName = "멕시칸 치킨 부리또")
        delay(300)
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
