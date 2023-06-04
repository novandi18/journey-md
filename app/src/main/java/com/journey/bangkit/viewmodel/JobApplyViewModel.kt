package com.journey.bangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.repository.JobApplyRepository
import com.journey.bangkit.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class JobApplyViewModel(private val repository: JobApplyRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Vacancy>>> = MutableStateFlow(UiState.Loading)
    val vacancies: StateFlow<UiState<List<Vacancy>>> get() = _uiState

    fun getJobApply() {
        viewModelScope.launch {
            repository.getJobApplies()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _uiState.value = UiState.Success(result)
                }
        }
    }
}