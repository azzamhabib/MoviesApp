package com.az.moviesapp.core.data.network.error_handling

import com.az.moviesapp.core.data.network.ApiError


sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(
        val code: Int? = null,
        val errorBody: String? = null,
        val errors: List<ApiError> = listOf()
    ) : Result<Nothing>()
}

