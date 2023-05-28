package com.journey.bangkit.repository

import com.journey.bangkit.data.model.Disability
import com.journey.bangkit.data.source.DisabilityDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class JobSeekerRepository {
    fun getDisabilityData(): Flow<List<Disability>> = flowOf(DisabilityDataSource.disabilities)

    companion object {
        @Volatile
        private var instance: JobSeekerRepository? = null

        fun getInstance(): JobSeekerRepository = instance ?: synchronized(this) {
            JobSeekerRepository().apply {
                instance = this
            }
        }
    }
}