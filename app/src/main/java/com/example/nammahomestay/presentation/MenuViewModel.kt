package com.example.nammahomestay.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammahomestay.domain.model.Menu
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
class MenuViewModel @Inject constructor(
    private val repository: HomestayRepository
) : ViewModel() {

    val currentMenu: StateFlow<Menu?> = repository.getTodayMenu().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    private val _isUpdating = MutableStateFlow(false)
    val isUpdating = _isUpdating.asStateFlow()

    fun updateMenu(items: String, imageUrl: String?) {
        viewModelScope.launch {
            _isUpdating.value = true
            val menu = Menu(
                date = System.currentTimeMillis().toString(),
                items = items,
                imageUrl = imageUrl,
                isActive = true
            )
            repository.updateMenu(menu)
            _isUpdating.value = false
        }
    }
}
