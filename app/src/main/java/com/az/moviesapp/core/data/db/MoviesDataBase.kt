package com.az.moviesapp.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.az.moviesapp.core.data.network.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun getRunDao(): MoviesDao
}