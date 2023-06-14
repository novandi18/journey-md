package com.journey.bangkit.repository

import android.content.Context
import com.journey.bangkit.data.api.JourneyApi
import com.journey.bangkit.data.local.JourneyDatabase
import com.journey.bangkit.data.local.login.LoginEntity
import com.journey.bangkit.data.model.LoginJobProviderResponse
import com.journey.bangkit.data.model.LoginRequest
import com.journey.bangkit.data.store.JourneyDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LoginJobProviderRepository @Inject constructor(
    private val api: JourneyApi,
    private val db: JourneyDatabase
) {
    suspend fun doLogin(email: String, password: String): Flow<LoginJobProviderResponse> {
        val request = api.loginJobProvider(
            LoginRequest(email, password)
        )

        db.loginDao.saveLogin(
            LoginEntity(user_id = request.id_company, role_id = request.role_id, token = request.token)
        )

        db.authDao.setIsLoginAsJobProvider(isLoggedIn = true)

        return flowOf(request)
    }
}