package com.kbsc.data.dto

data class ReviewDto(
    val id: String = "",
    val user: UserDto = UserDto(),
    val receiptPreview: ReceiptPreviewDto = ReceiptPreviewDto(),
    val region: String = "",
    val content: String = "",
    val photos: List<String>? = null,
    val createdAt: Long = 0L
)
