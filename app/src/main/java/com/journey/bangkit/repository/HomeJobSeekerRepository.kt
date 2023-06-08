package com.journey.bangkit.repository

import com.journey.bangkit.data.model.VacancyResponse
import com.journey.bangkit.data.source.VacancyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeJobSeekerRepository {
    fun getAllVacancies(): Flow<List<VacancyResponse>> {
        return flowOf(listOf(VacancyDataSource.vacanciesDummy()))
    }



//    private fun preProcess(context: Context, vacancies: List<Vacancy>, disability: Int, skill_one: Int, skill_two: Int): IntArray {
//        val model = Model.newInstance(context)
//
//        // Creates inputs for reference.
//        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 100), DataType.STRING)
//
//        // Runs model inference and gets result.
//        val outputs = model.process(inputFeature0)
//        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//        // Releases model resources if no longer used.
//        model.close()
//    }

    companion object {
        @Volatile
        private var instance: HomeJobSeekerRepository? = null

        fun getInstance(): HomeJobSeekerRepository = instance ?: synchronized(this) {
            HomeJobSeekerRepository().apply {
                instance = this
            }
        }
    }
}