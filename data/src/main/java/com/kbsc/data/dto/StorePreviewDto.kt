package com.kbsc.data.dto

data class StorePreviewDto(
    val id: String,
    val photo: String?,
    val name: String,
    val category: String?,
    val description: String?,
    val maxDiscount: Int,
    val discountStartTime: Int?,
    val phone: String,
    val breakStartTime: Int?,
    val breakEndTime: Int?,
    val startTime: Int,
    val endTime: Int,
    val hasChallenge: Boolean
)
