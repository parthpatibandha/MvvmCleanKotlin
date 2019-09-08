package com.patibandha.movieapp.data.source

import com.patibandha.movieapp.data.models.FlickerImageListPRQ
import com.patibandha.movieapp.data.models.FlickerImageListRS
import kotlinx.coroutines.Deferred

interface HomeRemoteDataSource {
    fun getAllMovieList(flickerImageListPRQ: FlickerImageListPRQ): Deferred<FlickerImageListRS>
}