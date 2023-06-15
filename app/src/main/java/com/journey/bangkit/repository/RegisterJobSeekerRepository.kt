package com.journey.bangkit.repository

import android.content.Context
import com.journey.bangkit.data.api.JourneyApi
import com.journey.bangkit.data.model.Disability
import com.journey.bangkit.data.model.Skill
import com.journey.bangkit.data.model.UserJobSeeker
import com.journey.bangkit.data.model.UserRegisterResponse
import com.journey.bangkit.data.source.DisabilityDataSource
import com.journey.bangkit.utils.getSkills
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RegisterJobSeekerRepository @Inject constructor(
    private val api: JourneyApi
) {
    fun getDisabilitySkill(context: Context): Flow<Pair<List<Disability>, List<Skill>>> {
        val disabilities = DisabilityDataSource.disabilities
        val skills = getSkills(context)
        return flowOf(Pair(disabilities, skills))
    }

    suspend fun doRegister(data: UserJobSeeker): Flow<UserRegisterResponse> {
        return flowOf(
            api.registerJobSeeker(data)
        )
    }
}