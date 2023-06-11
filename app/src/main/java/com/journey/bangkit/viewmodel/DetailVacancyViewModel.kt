package com.journey.bangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.data.model.VacancyDetail
import com.journey.bangkit.repository.DetailVacancyRepository
import com.journey.bangkit.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVacancyViewModel @Inject
constructor(private val repository: DetailVacancyRepository) : ViewModel() {
    private val _vacancy: MutableStateFlow<UiState<VacancyDetail>> = MutableStateFlow(UiState.Loading)
    val vacancy: StateFlow<UiState<VacancyDetail>> get() = _vacancy

    fun getVacancy(id: String) {
        viewModelScope.launch {
            repository.getVacancyById(id)
                .catch {
                    _vacancy.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _vacancy.value = UiState.Success(result)
                }
        }
    }
}