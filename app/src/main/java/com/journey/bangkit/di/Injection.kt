package com.journey.bangkit.di

import com.journey.bangkit.repository.JobProviderRepository
import com.journey.bangkit.repository.JobSeekerRepository
import com.journey.bangkit.repository.OnBoardingRepository

object Injection {
    fun provideOnBoarding(): OnBoardingRepository = OnBoardingRepository.getInstance()
    fun provideJobSeeker(): JobSeekerRepository = JobSeekerRepository.getInstance()
    fun provideJobProvider(): JobProviderRepository = JobProviderRepository.getInstance()
}