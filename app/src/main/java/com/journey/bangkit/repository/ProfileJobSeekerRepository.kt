package com.journey.bangkit.repository

import com.journey.bangkit.data.model.User
import com.journey.bangkit.data.source.JourneyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProfileJobSeekerRepository {
    fun getUserData(): Flow<User> = flowOf(JourneyDataSource.user)

    companion object {
        @Volatile
        private var instance: ProfileJobSeekerRepository? = null

        fun getInstance(): ProfileJobSeekerRepository = instance ?: synchronized(this) {
            ProfileJobSeekerRepository().apply {
                instance = this
            }
        }
    }
}