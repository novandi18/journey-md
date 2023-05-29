package com.journey.bangkit.repository

import android.content.Context
import com.journey.bangkit.data.store.JourneyDataStore

class LoginJobSeekerRepository {
    suspend fun setTokenLogin(context: Context) {
        val dataStore = JourneyDataStore(context)
        dataStore.setLoginAsJobSeeker()
    }

    companion object {
        @Volatile
        private var instance: LoginJobSeekerRepository? = null

        fun getInstance(): LoginJobSeekerRepository = instance ?: synchronized(this) {
            LoginJobSeekerRepository().apply {
                instance = this
            }
        }
    }
}