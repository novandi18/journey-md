package com.journey.bangkit.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.journey.bangkit.data.model.City
import com.journey.bangkit.data.model.Province
import com.journey.bangkit.data.model.Sector
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

fun getProvince(context: Context): List<Province> {
    val province = getJsonDataFromAsset(context, "region/province.json")
    val gson = Gson()
    val listProvince = object : TypeToken<List<Province>>() {}.type

    return gson.fromJson(province, listProvince)
}

fun getCityById(context: Context, id: Int = 11): List<City> {
    val province = getJsonDataFromAsset(context, "region/cities/$id.json")
    val gson = Gson()
    val listCities = object : TypeToken<List<City>>() {}.type

    return gson.fromJson(province, listCities)
}

fun getSector(context: Context): List<Sector> {
    val sector = getJsonDataFromAsset(context, "sector.json")
    val gson = Gson()
    val listSector = object : TypeToken<List<Sector>>() {}.type

    return gson.fromJson(sector, listSector)
}