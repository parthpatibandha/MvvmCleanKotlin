package com.patibandha.movieapp.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.patibandha.movieapp.data.models.Movie

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(list: List<Movie>)

    @Query("SELECT * FROM movies")
    fun getAllMovieList(): List<Movie>

}