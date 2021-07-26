package com.example.room_and_compose.data.dao

import androidx.room.*
import com.example.room_and_compose.data.entities.Cities
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Insert()
    suspend fun insert(city: Cities)

    @Query("Select * from cities")
    fun getAllCities(): Flow<List<Cities>>

    @Update
    suspend fun update(city: Cities)

    @Delete
    suspend fun delete(city: Cities)
}