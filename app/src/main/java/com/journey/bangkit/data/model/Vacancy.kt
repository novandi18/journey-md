package com.journey.bangkit.data.model

data class VacancyResponse(
    val status: String,
    val page: Int,
    val limit: Int,
    val totalVacancies: Int,
    val totalPages: Int,
    var vacancies: List<Vacancy>
)

data class Vacancy(
    val id: Int,
    val placement_address: String,
    val description: String,
    val sector: String,
    val created_at: String,
    val updated_at: String,
    val disability_name: String,
    val deadline_time: String,
    val company_name: String,
)