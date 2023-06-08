package com.journey.bangkit.data.source

import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.data.model.VacancyResponse
import kotlin.random.Random

object VacancyDataSource {
    fun vacanciesDummy(): VacancyResponse {
        val data = VacancyResponse(
            status = "Success",
            page = 1,
            limit = 10,
            totalVacancies = 1,
            totalPages = 1,
            vacancies = mutableListOf()
        )
        val dataVacancy: MutableList<Vacancy> = mutableListOf()

        for (i in 1..100) {
            val randomDisability =
                DisabilityDataSource.disabilities[Random.nextInt(0, DisabilityDataSource.disabilities.size - 1)]

            dataVacancy.add(
                Vacancy(
                    id = i,
                    placement_address = "Position $i",
                    description = "Deskripsi $i",
                    sector = "Sector $i",
                    created_at = "2023-06-01T07:59:04.938Z",
                    updated_at = "2023-06-01T07:59:04.938Z",
                    disability_name = randomDisability.name,
                    deadline_time = "2023-05-29T09:14:30.000Z",
                    company_name = "Company $i"
                )
            )

            data.vacancies = dataVacancy
        }

        return data
    }
}