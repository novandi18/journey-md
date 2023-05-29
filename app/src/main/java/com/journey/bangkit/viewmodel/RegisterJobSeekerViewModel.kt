package com.journey.bangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.data.model.Disability
import com.journey.bangkit.repository.RegisterJobSeekerRepository
import com.journey.bangkit.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RegisterJobSeekerViewModel(private val repository: RegisterJobSeekerRepository) : ViewModel() {
    private val _disability: MutableStateFlow<UiState<List<Disability>>> = MutableStateFlow(UiState.Loading)
    val disability: StateFlow<UiState<List<Disability>>> get() = _disability

    fun getDisabilities() {
        viewModelScope.launch {
            repository.getDisabilityData()
                .catch {
                    _disability.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _disability.value = UiState.Success(result)
                }
        }
    }
}