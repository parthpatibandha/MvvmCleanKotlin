package com.patibandha.movieapp.presentation.home

import android.arch.lifecycle.MutableLiveData
import com.patibandha.movieapp.data.contract.HomeRepo
import com.patibandha.movieapp.data.models.FlickerImageListPRQ
import com.patibandha.movieapp.data.models.FlickerImageListRS
import com.patibandha.movieapp.data.models.Movie
import com.patibandha.movieapp.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class MovieListingViewModel constructor(private val homeRepo: HomeRepo) : BaseViewModel() {

    val movieListRSLiveData: MutableLiveData<FlickerImageListRS> = MutableLiveData()
    val movieListLocalLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val insertMovieListLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getMovieList(page : String) {
        launch {
            postValue(homeRepo.getAllMovieList(FlickerImageListPRQ(page)), movieListRSLiveData)
        }
    }

    fun insertMovieList(list: List<Movie>) {
        launch {
            postValue(homeRepo.insertMovieList(list), insertMovieListLiveData)
        }
    }

    fun getMovieListLocal() {
        launch {
            postValue(homeRepo.getAllMovieListLocal(), movieListLocalLiveData)
        }
    }

}