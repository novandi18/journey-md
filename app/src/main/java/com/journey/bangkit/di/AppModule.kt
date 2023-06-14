package com.journey.bangkit.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.journey.bangkit.data.local.JourneyDatabase
import com.journey.bangkit.data.local.vacancy.VacancyEntity
import com.journey.bangkit.data.api.JourneyApi
import com.journey.bangkit.data.remote.JourneyRemoteMediator
import com.journey.bangkit.viewmodel.VacancyCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideJourneyDatabase(@ApplicationContext context: Context): JourneyDatabase {
        return Room.databaseBuilder(
            context,
            JourneyDatabase::class.java,
            "vacancies.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideJourneyApi(): JourneyApi {
        return Retrofit.Builder()
            .baseUrl(JourneyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideJourneyPager(db: JourneyDatabase, api: JourneyApi, category: VacancyCategory): Pager<Int, VacancyEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = JourneyRemoteMediator(
                db = db,
                api = api,
                category = category.getCategory()
            ),
            pagingSourceFactory = {
                db.vacancyDao.pagingSource()
            }
        )
    }

    @Provides
    fun provideVacancyCategory(): VacancyCategory = VacancyCategory()
}