package com.journey.bangkit.repository

import android.content.Context
import com.journey.bangkit.data.store.JourneyDataStore

class OnBoardingRepository {
    suspend fun saveOnBoardingState(isCompleted: Boolean, context: Context) {
        val dataStore = JourneyDataStore(context)
        dataStore.saveOnBoardingState(isCompleted)
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