package com.journey.bangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.repository.HomeJobSeekerRepository
import com.journey.bangkit.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeJobSeekerViewModel(private val repository: HomeJobSeekerRepository) : ViewModel() {
    private val _vacancies: MutableStateFlow<UiState<List<Vacancy>>> = MutableStateFlow(UiState.Loading)
    val vacancies: StateFlow<UiState<List<Vacancy>>> get() = _vacancies

    fun getVacancies() {
        viewModelScope.launch {
            repository.getAllVacancies()
                .catch {
                    _vacancies.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _vacancies.value = UiState.Success(result)
                }
        }
    }
}