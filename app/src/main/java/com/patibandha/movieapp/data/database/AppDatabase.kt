package com.patibandha.movieapp.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.patibandha.movieapp.data.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}