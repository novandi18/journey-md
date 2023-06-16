package com.journey.bangkit.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.map
import com.journey.bangkit.data.api.JourneyApi
import com.journey.bangkit.data.api.JourneyMLApi
import com.journey.bangkit.data.local.JourneyDatabase
import com.journey.bangkit.data.local.vacancy.VacancyEntity
import com.journey.bangkit.data.mappers.toVacancy
import com.journey.bangkit.data.model.AllVacancy
import com.journey.bangkit.data.model.MachineLearning
import com.journey.bangkit.data.model.MachineLearningResponse
import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.data.source.DisabilityDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeJobSeekerRepository @Inject constructor(
    private val pager: Pager<Int, VacancyEntity>,
    private val db: JourneyDatabase,
    private val apiMl: JourneyMLApi,
    private val api: JourneyApi
) {
    fun getAllVacancies() = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toVacancy() }
        }

    suspend fun getRecommendedData(): Flow<Pair<List<String>, List<AllVacancy>>> {
        val user = db.loginDao.getLogin()
        val userData = api.getUser(token = user.token, id = user.user_id)
        val request = apiMl.getPredict(
            MachineLearning(
                skill_one = userData.user.skill_one_name.toString(),
                skill_two = userData.user.skill_two_name.toString(),
                id_disability = DisabilityDataSource.disabilities.filter { it.name == userData.user.disability_name }[0].id
            )
        )
        val allVacancies = api.getAllVacancyWithoutPager()
        return flowOf(Pair(request.predictions, allVacancies.vacancies))
    }

    suspend fun switchPage(page: Int) {
        db.pageDao.switchPage(page)
    }
}