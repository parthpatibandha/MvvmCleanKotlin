package com.patibandha.movieapp.data.ds

import com.patibandha.movieapp.data.database.MoviesDao
import com.patibandha.movieapp.data.models.Movie
import com.patibandha.movieapp.data.source.HomeLocalDataSource

class HomeLocalDS constructor(private val moviesDao: MoviesDao) : HomeLocalDataSource {

    override fun insertMovieList(list: List<Movie>): Boolean {
        moviesDao.insertMovieList(list)
        return true
    }

    override fun getAllMovieList(): List<Movie> {
        return moviesDao.getAllMovieList()
    }
}