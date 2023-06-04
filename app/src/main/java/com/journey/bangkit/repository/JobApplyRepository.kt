package com.journey.bangkit.repository

import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.data.source.VacancyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class JobApplyRepository {
    fun getJobApplies(): Flow<List<Vacancy>> = flowOf(VacancyDataSource.vacanciesDummy())

    companion object {
        @Volatile
        private var instance: JobApplyRepository? = null

        fun getInstance(): JobApplyRepository = instance ?: synchronized(this) {
            JobApplyRepository().apply {
                instance = this
            }
        }
    }
}