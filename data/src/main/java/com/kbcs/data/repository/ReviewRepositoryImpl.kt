package com.kbcs.data.repository

import com.kbcs.data.service.ReviewService
import com.kbsc.data.dto.ReviewDto
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewService: ReviewService
) : ReviewRepository {
    override suspend fun getReviewList(): Result<List<ReviewDto>> {
        return runCatching { reviewService.getReviewList() }
    }
}
