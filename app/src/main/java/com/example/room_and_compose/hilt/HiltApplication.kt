package com.example.room_and_compose.hilt

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.room_and_compose.data.database.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class HiltApplication : Application()

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext appContext: Context
    ): AppDb {
        return Room.databaseBuilder(
            appContext,
            AppDb::class.java,
            "bdComposeKotlin.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideRepository(db: AppDb) = db.CityDao()
}
