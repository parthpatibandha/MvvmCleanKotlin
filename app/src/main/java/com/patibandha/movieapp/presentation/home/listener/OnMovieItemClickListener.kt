package com.patibandha.movieapp.presentation.home.listener

import com.patibandha.movieapp.data.models.Movie

interface OnMovieItemClickListener {
    fun onMovieItemClick(movie : Movie)
}