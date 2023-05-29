package com.journey.bangkit.data.source

import com.journey.bangkit.data.model.Disability

object DisabilityDataSource {
    val disabilities = listOf(
        Disability(id = 1, name = "Visual Impairment"),
        Disability(id = 2, name = "Hearing Impairment"),
        Disability(id = 3, name = "Physical Disability"),
        Disability(id = 4, name = "Learning Disability"),
        Disability(id = 5, name = "Cognitive Disability"),
        Disability(id = 6, name = "Hearing Disability")
    )
}