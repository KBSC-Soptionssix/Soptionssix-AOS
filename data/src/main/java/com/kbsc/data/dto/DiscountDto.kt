package com.kbsc.data.dto

data class DiscountDto(
    val storeList: List<StoreDto>?,
    val productList: List<ProductDto>?,
    val waitDonationList: List<ProductDto>?
)
