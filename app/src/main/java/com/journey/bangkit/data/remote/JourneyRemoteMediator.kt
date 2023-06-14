package com.journey.bangkit.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.journey.bangkit.data.api.JourneyApi
import com.journey.bangkit.data.local.JourneyDatabase
import com.journey.bangkit.data.local.vacancy.VacancyEntity
import com.journey.bangkit.data.mappers.toVacancyEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class JourneyRemoteMediator(
    private val db: JourneyDatabase,
    private val api: JourneyApi,
    private val category: Int
): RemoteMediator<Int, VacancyEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, VacancyEntity>
    ): MediatorResult {
        Log.d("okey", category.toString())
        return try {
            val pageSize = 1
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1 else pageSize + 1
                }
            }

//            val vacancies = when (category) {
//                0 -> api.getVacancies(page = loadKey)
//                1 -> api.getPopularVacancies(page = loadKey)
//                2 -> api.getLatestVacancies(page = loadKey)
//                else -> api.getVacancies(page = loadKey)
//            }

            val vacancies = api.getVacancies(page = loadKey)

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.vacancyDao.clearAll()
                }
                val vacancyEntities = vacancies.vacancies.map { it.toVacancyEntity() }
                db.vacancyDao.upsertAll(vacancyEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = vacancies.vacancies.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}