package com.journey.bangkit.repository

import com.journey.bangkit.data.api.JourneyApi
import com.journey.bangkit.data.local.JourneyDatabase
import com.journey.bangkit.data.model.Applicants
import com.journey.bangkit.data.model.ApplicantsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class JobApplicantRepository @Inject constructor(
    private val api: JourneyApi,
    private val db: JourneyDatabase
) {
    suspend fun getApplicants(): Flow<List<Applicants>> {
        val user = db.loginDao.getLogin()
        val request = api.getApplicants(
            token = user.token,
            companyId = user.user_id
        )
        return flowOf(request)
    }

    suspend fun doAccepted(applicantId: String): Flow<ApplicantsResponse> {
        val user = db.loginDao.getLogin()
        val request = api.postAcceptApplicants(
            applicantsId = applicantId,
            token = user.token,
            companyId = user.user_id
        )
        return flowOf(request)
    }

    suspend fun doRejected(applicantId: String): Flow<ApplicantsResponse> {
        val user = db.loginDao.getLogin()
        val request = api.postRejectApplicants(
            applicantsId = applicantId,
            token = user.token,
            companyId = user.user_id
        )
        return flowOf(request)
    }
}