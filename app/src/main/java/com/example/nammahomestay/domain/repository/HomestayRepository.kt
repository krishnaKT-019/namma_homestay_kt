package com.example.nammahomestay.domain.repository

import com.example.nammahomestay.domain.model.HomeProfile
import com.example.nammahomestay.domain.model.Inquiry
import com.example.nammahomestay.domain.model.LocalGuide
import com.example.nammahomestay.domain.model.Menu
import kotlinx.coroutines.flow.Flow

interface HomestayRepository {
    // Home Profile
    fun getHomeProfile(): Flow<HomeProfile?>
    suspend fun updateHomeProfile(profile: HomeProfile)
    
    // Menu
    fun getTodayMenu(): Flow<Menu?>
    suspend fun updateMenu(menu: Menu)
    
    // Inquiries
    fun getInquiries(): Flow<List<Inquiry>>
    
    // Local Guide
    fun getLocalGuides(): Flow<List<LocalGuide>>
    suspend fun addLocalGuide(guide: LocalGuide)
}
