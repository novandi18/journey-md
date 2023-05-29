package com.journey.bangkit.data.model

data class Vacancy(
    val id: Int,
    val position: String,
    val company: String,
    val logo: String,
    val expiredAt: String,
    val disability: List<Disability>
)
