package com.journey.bangkit.repository

import android.content.Context
import com.journey.bangkit.data.store.JourneyDataStore

class LoginJobProviderRepository {
    suspend fun setTokenLogin(context: Context) {
        val dataStore = JourneyDataStore(context)
        dataStore.setLoginAsJobProvider()
    }

    companion object {
        @Volatile
        private var instance: LoginJobProviderRepository? = null

        fun getInstance(): LoginJobProviderRepository = instance ?: synchronized(this) {
            LoginJobProviderRepository().apply {
                instance = this
            }
        }
    }
}