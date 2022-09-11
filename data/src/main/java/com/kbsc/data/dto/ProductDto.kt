package com.kbsc.data.dto

data class ProductDto(
    val id: String = "",
    val storePreview: StorePreviewDto = StorePreviewDto(),
    val photo: String? = null,
    val name: String = "안녕",
    val stockCount: Int = 0,
    val price: Int = 0,
    val discount: Int = 0,
    val donationCompleteCount: Int? = null,
    val donationWaitCount: Int? = null
)
