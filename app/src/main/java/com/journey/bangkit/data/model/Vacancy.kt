package com.journey.bangkit.data.model

data class VacancyResponse(
    val status: String,
    val page: Int,
    val limit: Int,
    val totalVacancies: Int,
    val totalPages: Int,
    var vacancies: List<Vacancy>
)

data class VacancyDetail(
    val status: String = "",
    val vacancy: Vacancy
)

data class Vacancy(
    val id: String = "",
    val placement_address: String = "",
    val description: String = "",
    val created_at: String = "",
    val updated_at: String = "",
    val deadline_time: String = "",
    val job_type: Int = 0,
    val skill_one_name: String = "",
    val skill_two_name: String = "",
    val disability_name: String = "",
    val company_logo: String = "",
    val sector_name: String = "",
    val company_name: String = ""
)