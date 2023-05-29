package com.journey.bangkit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.repository.LoginJobSeekerRepository
import kotlinx.coroutines.launch

class LoginJobSeekerViewModel(private val repository: LoginJobSeekerRepository) : ViewModel() {
    fun saveTokenLogin(context: Context) {
        viewModelScope.launch {
            repository.setTokenLogin(context)
        }
    }
}