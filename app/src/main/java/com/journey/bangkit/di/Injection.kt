package com.journey.bangkit.di

import com.journey.bangkit.repository.HomeJobProviderRepository
import com.journey.bangkit.repository.HomeJobSeekerRepository
import com.journey.bangkit.repository.JobApplyRepository
import com.journey.bangkit.repository.LoginJobProviderRepository
import com.journey.bangkit.repository.LoginJobSeekerRepository
import com.journey.bangkit.repository.OnBoardingRepository
import com.journey.bangkit.repository.ProfileJobSeekerRepository
import com.journey.bangkit.repository.RegisterJobProviderRepository
import com.journey.bangkit.repository.RegisterJobSeekerRepository

object Injection {
    fun provideOnBoarding(): OnBoardingRepository = OnBoardingRepository.getInstance()
    fun provideLoginJobSeeker(): LoginJobSeekerRepository = LoginJobSeekerRepository.getInstance()
    fun provideLoginJobProvider(): LoginJobProviderRepository = LoginJobProviderRepository.getInstance()
    fun provideRegisterJobSeeker(): RegisterJobSeekerRepository = RegisterJobSeekerRepository.getInstance()
    fun provideRegisterJobProvider(): RegisterJobProviderRepository = RegisterJobProviderRepository.getInstance()
    fun provideHomeJobSeeker(): HomeJobSeekerRepository = HomeJobSeekerRepository.getInstance()
    fun provideJobApply(): JobApplyRepository = JobApplyRepository.getInstance()
    fun provideProfileJobSeeker(): ProfileJobSeekerRepository = ProfileJobSeekerRepository.getInstance()
    fun provideHomeJobProvider(): HomeJobProviderRepository = HomeJobProviderRepository.getInstance()
}