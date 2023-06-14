package com.journey.bangkit.repository

import android.content.Context
import com.journey.bangkit.data.api.JourneyApi
import com.journey.bangkit.data.local.JourneyDatabase
import com.journey.bangkit.data.local.login.LoginEntity
import com.journey.bangkit.data.model.LoginJobSeekerResponse
import com.journey.bangkit.data.model.LoginRequest
import com.journey.bangkit.data.store.JourneyDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LoginJobSeekerRepository @Inject constructor(
    private val api: JourneyApi,
    private val db: JourneyDatabase
) {
    suspend fun doLogin(email: String, password: String): Flow<LoginJobSeekerResponse> {
        val request = api.loginJobSeeker(
            LoginRequest(email, password)
        )
        db.loginDao.saveLogin(
            LoginEntity(user_id = request.id_user, token = request.token, role_id = request.role_id)
        )
        db.authDao.setIsLoginAsJobSeeker(isLoggedIn = true)

        return flowOf(request)
    }
}