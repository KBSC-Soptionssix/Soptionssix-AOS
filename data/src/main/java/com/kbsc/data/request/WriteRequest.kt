package com.kbsc.data.request

data class WriteRequest(
    val region: String,
    val receiptId: String,
    val content: String,
    val photo: List<String>? = null
)
