package com.journey.bangkit.repository

import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.data.source.VacancyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeJobSeekerRepository {
    fun getAllVacancies(): Flow<List<Vacancy>> = flowOf(VacancyDataSource.vacanciesDummy())

    companion object {
        @Volatile
        private var instance: HomeJobSeekerRepository? = null

        fun getInstance(): HomeJobSeekerRepository = instance ?: synchronized(this) {
            HomeJobSeekerRepository().apply {
                instance = this
            }
        }
    }
}