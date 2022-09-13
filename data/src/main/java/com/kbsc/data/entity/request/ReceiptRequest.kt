package com.kbsc.data.entity.request

data class ReceiptRequest(
    val phone: String,
    val storeId: String,
    val productId: String,
    val productCount: Int,
    val pickUpTime: Long,
    val paymentMethod: String,
    val isChallenge: Boolean,
    val isDonate: Boolean
)
