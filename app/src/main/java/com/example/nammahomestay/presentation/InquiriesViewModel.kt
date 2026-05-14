package com.example.nammahomestay.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammahomestay.domain.model.Inquiry
import com.example.nammahomestay.domain.repository.HomestayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InquiriesViewModel @Inject constructor(
    private val repository: HomestayRepository
) : ViewModel() {

    val inquiries: StateFlow<List<Inquiry>> = repository.getInquiries().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}
