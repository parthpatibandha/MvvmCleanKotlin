package com.patibandha.movieapp.data.ds

import com.patibandha.movieapp.data.models.FlickerImageListPRQ
import com.patibandha.movieapp.data.models.FlickerImageListRS
import com.patibandha.movieapp.data.source.HomeRemoteDataSource
import com.patibandha.movieapp.domain.MovieApiService
import kotlinx.coroutines.Deferred

class HomeRemoteDS constructor(private val movieApiService: MovieApiService) :
    HomeRemoteDataSource {

    override fun getAllMovieList(flickerImageListPRQ: FlickerImageListPRQ): Deferred<FlickerImageListRS> {
        return movieApiService.getAllMovieList(
            flickerImageListPRQ.page
        )
    }
}