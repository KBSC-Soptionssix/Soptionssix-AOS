package com.kbsc.data.dto

data class ProductDto(
    val id: String,
    val storePreview: StorePreviewDto,
    val photo: String?,
    val name: String,
    val stockCount: Int,
    val price: Int,
    val discount: Int,
    val donationCompleteCount: Int?,
    val donationWaitCount: Int?
)
