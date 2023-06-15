package com.journey.bangkit.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.journey.bangkit.di.Injection
import com.journey.bangkit.viewmodel.HomeJobProviderViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeJobProviderViewModel::class.java)) {
            return HomeJobProviderViewModel(Injection.provideHomeJobProvider()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}