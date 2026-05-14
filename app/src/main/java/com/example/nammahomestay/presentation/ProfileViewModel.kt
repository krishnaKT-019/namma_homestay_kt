package com.example.nammahomestay.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammahomestay.domain.model.HomeProfile
import com.example.nammahomestay.domain.repository.HomestayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: HomestayRepository
) : ViewModel() {

    val profileState: StateFlow<HomeProfile?> = repository.getHomeProfile().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeProfile()
    )

    private val _isSaving = MutableStateFlow(false)
    val isSaving = _isSaving.asStateFlow()

    fun updateProfile(profile: HomeProfile) {
        viewModelScope.launch {
            _isSaving.value = true
            repository.updateHomeProfile(profile)
            _isSaving.value = false
        }
    }
}
