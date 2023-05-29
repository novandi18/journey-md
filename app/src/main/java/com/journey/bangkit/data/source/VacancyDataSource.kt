package com.journey.bangkit.data.source

import com.journey.bangkit.data.model.Disability
import com.journey.bangkit.data.model.Vacancy
import kotlin.random.Random

object VacancyDataSource {
    fun vacanciesDummy(): List<Vacancy> {
        val data: MutableList<Vacancy> = mutableListOf()
        for (i in 1..10) {
            val randomDisability = List(DisabilityDataSource.disabilities.size) {
                Random.nextInt(0, DisabilityDataSource.disabilities.size - 1)
            }

            val disability: MutableList<Disability> = mutableListOf()
            for (x in randomDisability) {
                disability.add(DisabilityDataSource.disabilities[x])
            }

            data.add(
                Vacancy(
                    id = i,
                    position = "Position $i",
                    company = "Company $i",
                    logo = "https://journey-apis.appspot.com.storage.googleapis.com/1edc1937171707d6a3cf994eb375c68065cae7dd.png",
                    expiredAt = "2 hari lagi",
                    disability = disability
                )
            )

            disability.clear()
        }

        return data
    }
}