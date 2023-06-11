package com.journey.bangkit.repository

import com.journey.bangkit.data.api.JourneyApi
import com.journey.bangkit.data.model.VacancyDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DetailVacancyRepository @Inject constructor(
    private val journeyApi: JourneyApi
) {
    suspend fun getVacancyById(id: String): Flow<VacancyDetail> = flowOf(journeyApi.getVacancy(id))
}