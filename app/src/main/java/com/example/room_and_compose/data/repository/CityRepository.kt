package com.example.room_and_compose.data.repository

import com.example.room_and_compose.data.database.AppDb
import com.example.room_and_compose.data.entities.Cities
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CityRepository @Inject constructor(appDb: AppDb) {
    private val dao = appDb.CityDao()

    val getAllCities: Flow<List<Cities>> = dao.getAllCities()

    suspend fun addCity(c: Cities) {
        dao.insert(c)
    }

    suspend fun updateCity(c: Cities) {
        dao.update(c)
    }

    suspend fun removeCity(c: Cities) {
        dao.delete(c)
    }
}