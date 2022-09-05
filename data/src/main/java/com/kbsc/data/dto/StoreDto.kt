package com.kbsc.data.dto

data class StoreDto(
    val id: String = "",
    val photo: String? = null,
    val name: String = "",
    val category: String? = "",
    val description: String? = "",
    val maxDiscount: Int = 0,
    val discountStartTime: Int? = null,
    val phone: String = "",
    val breakStartTime: Int? = null,
    val breakEndTime: Int? = null,
    val startTime: Int = 0,
    val endTime: Int = 0,
    val loadAddress: String = "",
    val address: String = "",
    val mapX: String = "",
    val mapY: String = "",
    val hasChallenge: Boolean = true
)
