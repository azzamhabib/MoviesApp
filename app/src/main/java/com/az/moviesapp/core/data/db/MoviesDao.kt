package com.az.moviesapp.core.data.db

import androidx.room.*
import com.az.moviesapp.core.data.network.model.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Transaction
    @Query("SELECT * FROM Movie")
    fun getAllMovies(): List<Movie>


    @Transaction
    @Query("SELECT * FROM Movie where isFavorite = :isFavorite and isBlocked = NOT :isFavorite ")
    fun getAllFavouriteMovies(isFavorite: Boolean): List<Movie>

    @Transaction
    @Query("SELECT * FROM Movie where isBlocked = :isBlocked")
    fun getAllBlockedMovies(isBlocked: Boolean): List<Movie>
}