package com.journey.bangkit.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JourneyDataStore(private val context: Context) {
    val isOnBoardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[ON_BOARDING_KEY] ?: false
        }

    suspend fun saveOnBoardingState(isCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ON_BOARDING_KEY] = isCompleted
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("JourneyDataStore")
        val ON_BOARDING_KEY = booleanPreferencesKey("on_boarding")
    }
}
