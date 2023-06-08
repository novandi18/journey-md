package com.journey.bangkit.repository

import android.content.Context
import com.journey.bangkit.data.store.JourneyDataStore
import kotlinx.coroutines.flow.Flow

class OnBoardingRepository {
    suspend fun saveOnBoardingState(isCompleted: Boolean, context: Context) {
        val dataStore = JourneyDataStore(context)
        dataStore.saveOnBoardingState(isCompleted)
    }

    fun getOnBoardingState(context: Context): Flow<Boolean> {
        val dataStore = JourneyDataStore(context)
        return dataStore.isOnBoardingCompleted
    }

    companion object {
        @Volatile
        private var instance: OnBoardingRepository? = null

        fun getInstance(): OnBoardingRepository = instance ?: synchronized(this) {
            OnBoardingRepository().apply {
                instance = this
            }
        }
    }
}