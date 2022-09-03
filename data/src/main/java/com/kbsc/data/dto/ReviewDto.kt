package com.kbsc.data.dto

data class ReviewDto(
    val id: String,
    val user: UserDto,
    val receiptPreview: ReceiptPreviewDto,
    val region: String,
    val content: String,
    val photos: List<String>?,
    val createdAt: Long
)
