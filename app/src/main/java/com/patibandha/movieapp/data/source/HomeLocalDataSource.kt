package com.patibandha.movieapp.data.source

import com.patibandha.movieapp.data.models.Movie

interface HomeLocalDataSource {
    fun insertMovieList(list : List<Movie>) : Boolean
    fun getAllMovieList(): List<Movie>
}