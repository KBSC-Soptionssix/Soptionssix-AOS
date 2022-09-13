package com.kbcs.data.repository

import com.kbsc.data.dto.ReviewDto

interface ReviewRepository {
    suspend fun getReviewList(): Result<List<ReviewDto>>
}
