package com.az.moviesapp.core.data.network.error_handling

import android.util.Log
import com.az.moviesapp.core.data.network.consumeError


import retrofit2.Response
import java.lang.Exception
import kotlinx.coroutines.CancellationException


private fun <T> parseResponse(response: Response<T>): Result<T?> {
    return if (response.isSuccessful) {
        Result.Success(response.body())
    } else {
        val errorMessage = response.errorBody()?.string()
        //EventBus.getDefault().post(GlobalServerErrorEvent(errorMessage))
        Result.Error(
            code = response.code(),
            errorBody = errorMessage,
            errors = consumeError(errorMessage ?: "")
        )
    }
}

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T?> {
    return try {
        parseResponse(apiCall.invoke())
    } catch (e: Exception) {
        Log.e("Enaya : ", Log.getStackTraceString(e))
        when (e) {
            !is CancellationException -> {}
        }
       Result.Error(errorBody = e.message)
    }
}