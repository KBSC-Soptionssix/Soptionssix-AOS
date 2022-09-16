package com.kbsc.data.service

import com.kbsc.data.dto.ReviewDto
import com.kbsc.data.request.WriteRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewService {
    @GET("/review")
    suspend fun getReviewList(): List<ReviewDto>

    @GET("/review/{id}")
    suspend fun getReview(
        @Path("id") id: String
    ): ReviewDto

    @POST("/review")
    suspend fun postReview(
        @Body body: WriteRequest
    )
}
