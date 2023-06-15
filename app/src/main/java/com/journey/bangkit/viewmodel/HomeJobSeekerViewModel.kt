package com.journey.bangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.repository.HomeJobSeekerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeJobSeekerViewModel @Inject
constructor(private val repository: HomeJobSeekerRepository) : ViewModel() {
    val vacancies: Flow<PagingData<Vacancy>> = repository.getAllVacancies().cachedIn(viewModelScope)

    suspend fun setVacancyCategory(page: Int) {
        repository.switchPage(page)
    }
}