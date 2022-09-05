package com.kbsc.data.dto

data class ReceiptPreviewDto(
    val id: String = "",
    val userId: String = "",
    val product: ProductDto = ProductDto(),
    val productCount: Int = 0,
    val date: Long = 0L,
    val pickUpTime: Long = 0L,
    val paymentMethod: String = "",
    val isChallenge: Boolean = false,
    val isDonate: Boolean = false
)
