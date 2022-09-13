package com.kbcs.data.service

import com.kbsc.data.dto.ReviewDto
import retrofit2.http.GET

interface ReviewService {
    @GET("/review")
    suspend fun getReviewList(): List<ReviewDto>
}
