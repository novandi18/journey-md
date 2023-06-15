package com.journey.bangkit.di

import com.journey.bangkit.repository.HomeJobProviderRepository

object Injection {
    fun provideHomeJobProvider(): HomeJobProviderRepository = HomeJobProviderRepository.getInstance()
}