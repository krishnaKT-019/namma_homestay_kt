package com.example.nammahomestay.domain.model

data class Inquiry(
    val id: String = "",
    val travelerName: String = "",
    val message: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val phoneNumber: String = ""
)
