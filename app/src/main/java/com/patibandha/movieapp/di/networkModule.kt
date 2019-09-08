package com.patibandha.movieapp.di

import com.google.gson.Gson
import com.patibandha.movieapp.BuildConfig
import com.patibandha.movieapp.domain.MovieApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.patibandha.movieapp.domain.GsonPConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { createOkHttpClient() }

    single { createWebService<MovieApiService>(get(), "https://www.flickr.com/services/feeds/") }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if(BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
           // .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonPConverterFactory(Gson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    return retrofit.create(T::class.java)
}