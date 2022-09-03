package com.kbsc.data.dto

data class StorePreviewDto(
    val id: String = "",
    val photo: String? = null,
    val name: String = "",
    val category: String? = null,
    val description: String? = null,
    val maxDiscount: Int = 0,
    val discountStartTime: Int? = null,
    val phone: String = "",
    val breakStartTime: Int? = null,
    val breakEndTime: Int? = null,
    val startTime: Int = 0,
    val endTime: Int = 0,
    val hasChallenge: Boolean = true
)
