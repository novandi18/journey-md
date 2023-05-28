package com.journey.bangkit.repository

import android.content.Context
import com.journey.bangkit.data.model.City
import com.journey.bangkit.data.model.Province
import com.journey.bangkit.data.model.Sector
import com.journey.bangkit.utils.getCityById
import com.journey.bangkit.utils.getProvince
import com.journey.bangkit.utils.getSector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class JobProviderRepository {
    fun getProvinceCitySector(context: Context, provinceId: Int)
    : Flow<Triple<List<Province>,List<City>, List<Sector>>> {
        val province = getProvince(context)
        val city = getCityById(context = context, id = provinceId)
        val sector = getSector(context)
        return flowOf(Triple(province, city, sector))
    }

    companion object {
        @Volatile
        private var instance: JobProviderRepository? = null

        fun getInstance(): JobProviderRepository = instance ?: synchronized(this) {
            JobProviderRepository().apply {
                instance = this
            }
        }
    }
}