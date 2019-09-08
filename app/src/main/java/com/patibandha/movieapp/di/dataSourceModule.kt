package com.patibandha.movieapp.di

import com.patibandha.movieapp.data.ds.HomeLocalDS
import com.patibandha.movieapp.data.ds.HomeRemoteDS
import com.patibandha.movieapp.data.source.HomeLocalDataSource
import com.patibandha.movieapp.data.source.HomeRemoteDataSource
import org.koin.dsl.module.module

val dataSourceModule = module {
    single { HomeLocalDS(get()) as HomeLocalDataSource }
    single { HomeRemoteDS(get()) as HomeRemoteDataSource }
}