package com.kbsc.data.dto

data class StoreDto(
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
    val loadAddress: String,
    val address: String,
    val mapX: String,
    val mapY: String,
    val hasChallenge: Boolean
)
