package com.example.nammahomestay.domain.model

data class HomeProfile(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val pricePerNight: Double = 0.0,
    val roomImages: List<String> = emptyList(),
    val toiletImages: List<String> = emptyList(),
    val surroundingsImages: List<String> = emptyList(),
    val facilities: List<String> = emptyList(),
    val verificationChecklist: Map<String, Boolean> = mapOf(
        "Clean Rooms" to false,
        "Clean Toilets" to false,
        "Safe Drinking Water" to false,
        "Basic Hygiene" to false,
        "Friendly Environment" to false
    )
)
