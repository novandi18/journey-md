package com.journey.bangkit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.repository.OnBoardingRepository
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val repository: OnBoardingRepository) : ViewModel() {
    fun saveOnBoardingState(isCompleted: Boolean, context: Context) {
        viewModelScope.launch {
            repository.saveOnBoardingState(
                isCompleted = isCompleted,
                context = context
            )
        }
    }
}