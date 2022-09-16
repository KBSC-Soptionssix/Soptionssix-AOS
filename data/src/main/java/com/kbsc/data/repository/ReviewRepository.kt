package com.kbsc.data.repository

import com.kbsc.data.dto.ReviewDto
import com.kbsc.data.request.WriteRequest

interface ReviewRepository {
    suspend fun getReviewList(): Result<List<ReviewDto>>
    suspend fun getReview(id: String): Result<ReviewDto>
    suspend fun postReview(writeRequest: WriteRequest)
}
