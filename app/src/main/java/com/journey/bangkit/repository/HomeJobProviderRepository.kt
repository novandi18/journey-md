package com.journey.bangkit.repository

import com.journey.bangkit.data.model.VacancyResponse
import com.journey.bangkit.data.source.VacancyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeJobProviderRepository {
    fun getVacancyCompany(): Flow<List<VacancyResponse>> = flowOf(listOf(VacancyDataSource.vacanciesDummy()))

    companion object {
        @Volatile
        private var instance: HomeJobProviderRepository? = null

        fun getInstance(): HomeJobProviderRepository = instance ?: synchronized(this) {
            HomeJobProviderRepository().apply {
                instance = this
            }
        }
    }
}