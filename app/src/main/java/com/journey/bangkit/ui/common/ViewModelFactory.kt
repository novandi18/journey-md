package com.journey.bangkit.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.journey.bangkit.di.Injection
import com.journey.bangkit.viewmodel.OnBoardingViewModel
import com.journey.bangkit.viewmodel.RegisterJobProviderViewModel
import com.journey.bangkit.viewmodel.RegisterJobSeekerViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnBoardingViewModel::class.java)) {
            return OnBoardingViewModel(Injection.provideOnBoarding()) as T
        } else if (modelClass.isAssignableFrom(RegisterJobSeekerViewModel::class.java)) {
            return RegisterJobSeekerViewModel(Injection.provideJobSeeker()) as T
        } else if (modelClass.isAssignableFrom(RegisterJobProviderViewModel::class.java)) {
            return RegisterJobProviderViewModel(Injection.provideJobProvider()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}