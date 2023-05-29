package com.journey.bangkit.repository

import com.journey.bangkit.data.model.Disability
import com.journey.bangkit.data.source.DisabilityDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RegisterJobSeekerRepository {
    fun getDisabilityData(): Flow<List<Disability>> = flowOf(DisabilityDataSource.disabilities)

    companion object {
        @Volatile
        private var instance: RegisterJobSeekerRepository? = null

        fun getInstance(): RegisterJobSeekerRepository = instance ?: synchronized(this) {
            RegisterJobSeekerRepository().apply {
                instance = this
            }
        }
    }
}