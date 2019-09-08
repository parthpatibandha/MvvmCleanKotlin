package com.patibandha.movieapp.data.repository

import com.patibandha.movieapp.data.Either
import com.patibandha.movieapp.data.contract.HomeRepo
import com.patibandha.movieapp.data.models.FlickerImageListPRQ
import com.patibandha.movieapp.data.models.FlickerImageListRS
import com.patibandha.movieapp.data.models.Movie
import com.patibandha.movieapp.data.source.HomeLocalDataSource
import com.patibandha.movieapp.data.source.HomeRemoteDataSource

class HomeRepository constructor(
    private val homeLocalDataSource: HomeLocalDataSource,
    private val homeRemoteDataSource: HomeRemoteDataSource
) : BaseRepository(), HomeRepo {


    override suspend fun getAllMovieList(flickerImageListPRQ: FlickerImageListPRQ): Either<MyAppException, FlickerImageListRS> {
        return either(homeRemoteDataSource.getAllMovieList(flickerImageListPRQ))
    }

    override suspend fun insertMovieList(list: List<Movie>): Either<MyAppException, Boolean> {
        return eitherLocal(homeLocalDataSource.insertMovieList(list))
    }

    override suspend fun getAllMovieListLocal(): Either<MyAppException, List<Movie>> {
        return eitherLocal(homeLocalDataSource.getAllMovieList())
    }
}