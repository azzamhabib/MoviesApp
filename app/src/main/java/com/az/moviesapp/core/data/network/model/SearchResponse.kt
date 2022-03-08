package com.az.moviesapp.core.data.network.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results") val results: List<Movie>?,
    @SerializedName("expression") val expression: String?,
)
