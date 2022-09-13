package com.kbcs.data.service

import com.kbsc.data.dto.ReviewDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewService {
    @GET("/review")
    suspend fun getReviewList(): List<ReviewDto>

    @GET("/review/{id}")
    suspend fun getReview(
        @Path("id") id: String
    ): ReviewDto
}
