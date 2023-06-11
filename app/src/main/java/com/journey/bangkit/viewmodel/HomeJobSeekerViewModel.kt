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
constructor(repository: HomeJobSeekerRepository, private val category: VacancyCategory) : ViewModel() {
    val vacancies: Flow<PagingData<Vacancy>> = repository.getAllVacancies().cachedIn(viewModelScope)

    fun setVacancyCategory(categorySelected: Int = 0) {
        category.setCategory(categorySelected)
    }
}

class VacancyCategory @Inject constructor() {
    private var category: Int = 0

    fun setCategory(categorySelected: Int) {
        category = categorySelected
    }

    fun getCategory(): Int = category
}