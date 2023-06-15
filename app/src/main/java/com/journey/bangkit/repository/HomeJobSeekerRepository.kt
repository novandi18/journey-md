package com.journey.bangkit.repository

import androidx.paging.Pager
import androidx.paging.map
import com.journey.bangkit.data.local.JourneyDatabase
import com.journey.bangkit.data.local.vacancy.VacancyEntity
import com.journey.bangkit.data.mappers.toVacancy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeJobSeekerRepository @Inject constructor(
    private val pager: Pager<Int, VacancyEntity>,
    private val db: JourneyDatabase
) {
    fun getAllVacancies() = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toVacancy() }
        }

    suspend fun switchPage(page: Int) {
        db.pageDao.switchPage(page)
    }
}