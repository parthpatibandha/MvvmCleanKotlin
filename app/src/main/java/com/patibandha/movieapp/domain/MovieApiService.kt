package com.patibandha.movieapp.domain

import com.patibandha.movieapp.data.models.FlickerImageListRS
import com.patibandha.movieapp.presentation.utility.ApiConstant
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface MovieApiService {

    @POST(ApiConstant.API_MOVIES)
    fun getAllMovieList(
        @Query("page") page : String
    ): Deferred<FlickerImageListRS>
}