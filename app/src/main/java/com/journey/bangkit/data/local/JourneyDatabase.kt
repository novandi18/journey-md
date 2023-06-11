package com.journey.bangkit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [VacancyEntity::class],
    version = 1
)
abstract class JourneyDatabase: RoomDatabase() {
    abstract val vacancyDao: VacancyDao
}