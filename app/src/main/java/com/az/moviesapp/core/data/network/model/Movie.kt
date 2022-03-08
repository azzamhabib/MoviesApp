package com.az.moviesapp.core.data.network.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.az.moviesapp.core.baseClasses.BaseModelView
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: String,
    @SerializedName("rank") val rank: String?,
    @SerializedName("rankUpDown") val rankUpDown: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("fullTitle") val fullTitle: String?,
    @SerializedName("year") val year: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("crew") val crew: String?,
    @SerializedName("imDbRating") val imDbRating: String?,
    @SerializedName("imDbRatingCount") val imDbRatingCount: String?,
    var isSelected: Boolean = false,
    var isFavorite: Boolean = false,
    var isBlocked: Boolean = false,
): BaseModelView()
