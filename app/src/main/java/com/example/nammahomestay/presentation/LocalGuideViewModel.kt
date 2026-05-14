package com.example.nammahomestay.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammahomestay.domain.model.LocalGuide
import com.example.nammahomestay.domain.repository.HomestayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalGuideViewModel @Inject constructor(
    private val repository: HomestayRepository
) : ViewModel() {

    val localGuides: StateFlow<List<LocalGuide>> = repository.getLocalGuides().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addGuide(name: String, description: String, distance: String) {
        viewModelScope.launch {
            val guide = LocalGuide(
                name = name,
                description = description,
                distance = distance
            )
            repository.addLocalGuide(guide)
        }
    }
}
