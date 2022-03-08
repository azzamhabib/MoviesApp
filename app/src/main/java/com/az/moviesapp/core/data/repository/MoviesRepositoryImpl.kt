package com.az.moviesapp.core.data.repository

import androidx.lifecycle.MutableLiveData
import com.az.moviesapp.core.data.db.MoviesDao
import com.az.moviesapp.core.data.network.Api
import com.az.moviesapp.core.data.network.error_handling.Result
import com.az.moviesapp.core.data.network.error_handling.safeApiCall
import com.az.moviesapp.core.data.network.model.Movie
import com.az.moviesapp.core.data.network.model.MoviesResponse
import com.az.moviesapp.core.data.network.model.SearchResponse

class MoviesRepositoryImpl(val api: Api , val moviesDao: MoviesDao): MoviesRepository {
    override suspend fun getMovies(): Result<MoviesResponse?> {
        return safeApiCall { api.getMovies() }
    }

    override suspend fun searchForMovie(expression: String): Result<SearchResponse?> {
        return safeApiCall { api.searchForMovie(expression) }
    }

    override  fun getFavoriteMovies(): List<Movie> {
        return moviesDao.getAllFavouriteMovies(true)
    }

    override  fun getBlockedMovies(): List<Movie> {
        return moviesDao.getAllBlockedMovies(true)
    }
}