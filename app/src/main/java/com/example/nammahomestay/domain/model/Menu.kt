package com.example.nammahomestay.domain.model

data class Menu(
    val id: String = "",
    val date: String = "", // ISO date string
    val items: String = "",
    val imageUrl: String? = null,
    val isActive: Boolean = true
)
