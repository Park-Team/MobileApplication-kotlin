package com.example.ezpark.domain.model

data class ParkingSpace(
    val id: String = "",
    val address: String = "",
    val length: Double = 0.0,
    val width: Double = 0.0,
    val height: Double = 0.0,
    val startTime: String = "",
    val endTime: String = "",
    val phone: String = "",
    val pricePerHour: Double = 0.0,
    val description: String = ""
)
