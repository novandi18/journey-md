package com.journey.bangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.data.model.VacancyResponse
import com.journey.bangkit.repository.HomeJobProviderRepository
import com.journey.bangkit.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeJobProviderViewModel(private val repository: HomeJobProviderRepository) : ViewModel() {
    private val _vacancies: MutableStateFlow<UiState<List<VacancyResponse>>> = MutableStateFlow(UiState.Loading)
    val vacancies: StateFlow<UiState<List<VacancyResponse>>> get() = _vacancies

    fun getVacancies() {
        viewModelScope.launch {
            repository.getVacancyCompany()
                .catch {
                    _vacancies.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _vacancies.value = UiState.Success(result)
                }
        }
    }
}