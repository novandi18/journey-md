package com.journey.bangkit.repository

import com.journey.bangkit.data.local.JourneyDatabase
import com.journey.bangkit.data.local.auth.AuthEntity
import javax.inject.Inject

class OnBoardingRepository @Inject constructor(
    private val db: JourneyDatabase
) {
    suspend fun upsertAll() {
        db.authDao.upsertAllAuth(AuthEntity())
    }

    suspend fun getAll(): AuthEntity = db.authDao.getOnBoardingAndAuth()

    suspend fun updateOnBoarding(isCompleted: Boolean) {
        db.authDao.setIsOnBoardingCompleted(isCompleted)
    }
}