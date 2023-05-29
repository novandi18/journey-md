package com.journey.bangkit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.repository.LoginJobProviderRepository
import kotlinx.coroutines.launch

class LoginJobProviderViewModel(private val repository: LoginJobProviderRepository) : ViewModel() {
    fun saveTokenLogin(context: Context) {
        viewModelScope.launch {
            repository.setTokenLogin(context)
        }
    }
}