package com.example.room_and_compose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room_and_compose.data.dao.CityDao
import com.example.room_and_compose.data.entities.Cities

@Database(entities = [Cities::class], version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {
    abstract fun CityDao(): CityDao
}