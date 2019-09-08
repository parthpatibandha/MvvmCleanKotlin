package com.patibandha.movieapp.data.contract

import com.patibandha.movieapp.data.Either
import com.patibandha.movieapp.data.models.FlickerImageListPRQ
import com.patibandha.movieapp.data.models.FlickerImageListRS
import com.patibandha.movieapp.data.models.Movie
import com.patibandha.movieapp.data.repository.BaseRepository

interface HomeRepo {
    //remote
    suspend fun getAllMovieList(flickerImageListPRQ: FlickerImageListPRQ): Either<BaseRepository.MyAppException, FlickerImageListRS>

    //local
    suspend fun insertMovieList(list : List<Movie>) : Either<BaseRepository.MyAppException, Boolean>
    suspend fun getAllMovieListLocal(): Either<BaseRepository.MyAppException, List<Movie>>
}