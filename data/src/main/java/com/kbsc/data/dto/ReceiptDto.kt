package com.kbsc.data.dto

data class ReceiptDto(
    val id: String,
    val userId: String,
    val store: StoreDto,
    val product: ProductDto,
    val review: ReviewDto?,
    val productCount: Int,
    val date: Long,
    val pickUpTime: Long,
    val paymentMethod: String,
    val isChallenge: Boolean,
    val isDonate: Boolean
)
