package com.journey.bangkit.di

import com.journey.bangkit.repository.HomeJobProviderRepository
import com.journey.bangkit.repository.JobApplyRepository
import com.journey.bangkit.repository.LoginJobProviderRepository
import com.journey.bangkit.repository.LoginJobSeekerRepository
import com.journey.bangkit.repository.ProfileJobSeekerRepository
import com.journey.bangkit.repository.RegisterJobProviderRepository

object Injection {
    fun provideJobApply(): JobApplyRepository = JobApplyRepository.getInstance()
    fun provideProfileJobSeeker(): ProfileJobSeekerRepository = ProfileJobSeekerRepository.getInstance()
    fun provideHomeJobProvider(): HomeJobProviderRepository = HomeJobProviderRepository.getInstance()
}