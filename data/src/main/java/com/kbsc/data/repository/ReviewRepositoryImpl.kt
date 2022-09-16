package com.kbsc.data.repository

import android.util.Log
import com.kbsc.data.service.ReviewService
import com.kbsc.data.dto.ReviewDto
import com.kbsc.data.request.WriteRequest
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewService: ReviewService
) : ReviewRepository {
    override suspend fun getReviewList(): Result<List<ReviewDto>> {
        return runCatching { reviewService.getReviewList() }
    }

    override suspend fun getReview(id: String): Result<ReviewDto> {
        return runCatching { reviewService.getReview(id) }
    }

    override suspend fun postReview(writeRequest: WriteRequest) {
        runCatching { reviewService.postReview(writeRequest) }
            .onSuccess { Log.d("PostReview", "POST 통신 성공") }
            .onFailure { Log.d("PostReview", "POST 통신 실패") }
    }
}
