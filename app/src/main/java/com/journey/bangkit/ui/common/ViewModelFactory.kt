package com.journey.bangkit.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.journey.bangkit.di.Injection
import com.journey.bangkit.viewmodel.HomeJobSeekerViewModel
import com.journey.bangkit.viewmodel.JobApplyViewModel
import com.journey.bangkit.viewmodel.LoginJobProviderViewModel
import com.journey.bangkit.viewmodel.LoginJobSeekerViewModel
import com.journey.bangkit.viewmodel.OnBoardingViewModel
import com.journey.bangkit.viewmodel.ProfileJobSeekerViewModel
import com.journey.bangkit.viewmodel.RegisterJobProviderViewModel
import com.journey.bangkit.viewmodel.RegisterJobSeekerViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnBoardingViewModel::class.java)) {
            return OnBoardingViewModel(Injection.provideOnBoarding()) as T
        } else if (modelClass.isAssignableFrom(RegisterJobSeekerViewModel::class.java)) {
            return RegisterJobSeekerViewModel(Injection.provideRegisterJobSeeker()) as T
        } else if (modelClass.isAssignableFrom(RegisterJobProviderViewModel::class.java)) {
            return RegisterJobProviderViewModel(Injection.provideRegisterJobProvider()) as T
        } else if (modelClass.isAssignableFrom(HomeJobSeekerViewModel::class.java)) {
            return HomeJobSeekerViewModel(Injection.provideHomeJobSeeker()) as T
        } else if (modelClass.isAssignableFrom(LoginJobSeekerViewModel::class.java)) {
            return LoginJobSeekerViewModel(Injection.provideLoginJobSeeker()) as T
        } else if (modelClass.isAssignableFrom(LoginJobProviderViewModel::class.java)) {
            return LoginJobProviderViewModel(Injection.provideLoginJobProvider()) as T
        } else if (modelClass.isAssignableFrom(JobApplyViewModel::class.java)) {
            return JobApplyViewModel(Injection.provideJobApply()) as T
        } else if (modelClass.isAssignableFrom(ProfileJobSeekerViewModel::class.java)) {
            return ProfileJobSeekerViewModel(Injection.provideProfileJobSeeker()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}