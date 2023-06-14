package com.journey.bangkit.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.journey.bangkit.di.Injection
import com.journey.bangkit.viewmodel.HomeJobProviderViewModel
import com.journey.bangkit.viewmodel.JobApplyViewModel
import com.journey.bangkit.viewmodel.ProfileJobSeekerViewModel
import com.journey.bangkit.viewmodel.RegisterJobProviderViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobApplyViewModel::class.java)) {
            return JobApplyViewModel(Injection.provideJobApply()) as T
        } else if (modelClass.isAssignableFrom(ProfileJobSeekerViewModel::class.java)) {
            return ProfileJobSeekerViewModel(Injection.provideProfileJobSeeker()) as T
        } else if (modelClass.isAssignableFrom(HomeJobProviderViewModel::class.java)) {
            return HomeJobProviderViewModel(Injection.provideHomeJobProvider()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}