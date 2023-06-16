package com.journey.bangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.journey.bangkit.data.model.AllVacancy
import com.journey.bangkit.data.model.MachineLearning
import com.journey.bangkit.data.model.MachineLearningResponse
import com.journey.bangkit.data.model.UserApplyStatusResponse
import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.repository.HomeJobSeekerRepository
import com.journey.bangkit.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeJobSeekerViewModel @Inject
constructor(private val repository: HomeJobSeekerRepository) : ViewModel() {
    val vacancies: Flow<PagingData<Vacancy>> = repository.getAllVacancies().cachedIn(viewModelScope)

    private val _predict: MutableStateFlow<UiState<Pair<List<String>, List<AllVacancy>>>> = MutableStateFlow(
        UiState.Loading)
    val predict: StateFlow<UiState<Pair<List<String>, List<AllVacancy>>>> get() = _predict

    suspend fun setVacancyCategory(page: Int) {
        repository.switchPage(page)
    }

    fun getPredict() {
        viewModelScope.launch {
            repository.getRecommendedData()
                .catch {
                    _predict.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _predict.value = UiState.Success(result)
                }
        }
    }
}