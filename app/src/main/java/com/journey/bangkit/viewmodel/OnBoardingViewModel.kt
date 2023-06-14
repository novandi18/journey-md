package com.journey.bangkit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.data.local.auth.AuthEntity
import com.journey.bangkit.repository.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repository: OnBoardingRepository
) : ViewModel() {
    suspend fun upsertAll() {
        repository.upsertAll()
    }

    suspend fun getAll(): AuthEntity = repository.getAll()

    suspend fun setOnBoardingIsCompleted(isCompleted: Boolean) {
        repository.updateOnBoarding(isCompleted)
    }
}