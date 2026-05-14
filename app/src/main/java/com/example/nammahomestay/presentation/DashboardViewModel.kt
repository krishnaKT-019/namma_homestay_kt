package com.example.nammahomestay.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammahomestay.domain.model.Menu
import com.example.nammahomestay.domain.repository.HomestayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: HomestayRepository
) : ViewModel() {

    val menuState: StateFlow<Menu?> = repository.getTodayMenu().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val inquiriesCount: StateFlow<Int> = repository.getInquiries()
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val hostName: StateFlow<String> = repository.getHomeProfile()
        .map { it?.name ?: "Host" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "Host")
}
