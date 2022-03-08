package com.az.moviesapp.core.data.network.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("items") val items: List<Movie>?,
    @SerializedName("errorMessage") val errorMessage: String?,
)
