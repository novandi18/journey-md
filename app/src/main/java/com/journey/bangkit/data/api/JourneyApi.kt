package com.journey.bangkit.data.api

import com.journey.bangkit.data.model.VacancyDetail
import com.journey.bangkit.data.model.VacancyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JourneyApi {
    @GET("vacancies")
    suspend fun getVacancies(
        @Query("key") key: String? = null,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10
    ) : VacancyResponse

    @GET("vacancies/latest")
    suspend fun getLatestVacancies(
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10
    ) : VacancyResponse

    @GET("vacancies/popular")
    suspend fun getPopularVacancies(
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10
    ) : VacancyResponse

    @GET("vacancies/{id}")
    suspend fun getVacancy(
        @Path("id") id: String
    ) : VacancyDetail

    companion object {
        const val BASE_URL = "https://companies-pchfpsfuwq-et.a.run.app/api/"
    }
}