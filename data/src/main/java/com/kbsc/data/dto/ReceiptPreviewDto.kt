package com.kbsc.data.dto

data class ReceiptPreviewDto(
    val id: String,
    val userId: String,
    val product: ProductDto,
    val productCount: Int,
    val date: Long,
    val pickUpTime: Long,
    val paymentMethod: String,
    val isChallenge: Boolean,
    val isDonate: Boolean
)
