package com.kbsc.data.request

data class WriteRequest(
    val region: String,
    val receipt: String,
    val content: String,
    val photo: List<String>? = null
)
