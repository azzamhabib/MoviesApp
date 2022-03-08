package com.az.moviesapp.core.data.repository

import androidx.lifecycle.MutableLiveData
import com.az.moviesapp.core.data.network.error_handling.Result
import com.az.moviesapp.core.data.network.model.Movie
import com.az.moviesapp.core.data.network.model.MoviesResponse
import com.az.moviesapp.core.data.network.model.SearchResponse
import retrofit2.http.Path


interface MoviesRepository {
    suspend fun getMovies(): Result<MoviesResponse?>
    suspend fun searchForMovie(expression: String): Result<SearchResponse?>
    fun getFavoriteMovies(): List<Movie>
    fun getBlockedMovies(): List<Movie>
}