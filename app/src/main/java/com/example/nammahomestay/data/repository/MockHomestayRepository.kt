package com.example.nammahomestay.data.repository

import com.example.nammahomestay.domain.model.HomeProfile
import com.example.nammahomestay.domain.model.Inquiry
import com.example.nammahomestay.domain.model.LocalGuide
import com.example.nammahomestay.domain.model.Menu
import com.example.nammahomestay.domain.repository.HomestayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MockHomestayRepository @Inject constructor() : HomestayRepository {

    private val _profile = MutableStateFlow(HomeProfile(name = "Malnad Home Stay", description = "A beautiful stay amidst coffee plantations."))
    private val _menu = MutableStateFlow<Menu?>(Menu(items = "Akki Rotti, Coconut Chutney, Filter Coffee"))
    private val _inquiries = MutableStateFlow(listOf(
        Inquiry(id = "1", travelerName = "Rahul Sharma", message = "Is there WiFi available?", phoneNumber = "9876543210"),
        Inquiry(id = "2", travelerName = "Sneha Kapur", message = "Can we get extra beds?", phoneNumber = "9123456789")
    ))
    private val _guides = MutableStateFlow(listOf(
        LocalGuide(id = "1", name = "Abbey Falls", description = "Beautiful waterfall nearby", distance = "5 km"),
        LocalGuide(id = "2", name = "Coffee Farm", description = "Experience coffee picking", distance = "2 km")
    ))

    override fun getHomeProfile(): Flow<HomeProfile?> = _profile.asStateFlow()
    override suspend fun updateHomeProfile(profile: HomeProfile) { _profile.value = profile }

    override fun getTodayMenu(): Flow<Menu?> = _menu.asStateFlow()
    override suspend fun updateMenu(menu: Menu) { _menu.value = menu }

    override fun getInquiries(): Flow<List<Inquiry>> = _inquiries.asStateFlow()

    override fun getLocalGuides(): Flow<List<LocalGuide>> = _guides.asStateFlow()
    override suspend fun addLocalGuide(guide: LocalGuide) {
        _guides.value = _guides.value + guide
    }
}
