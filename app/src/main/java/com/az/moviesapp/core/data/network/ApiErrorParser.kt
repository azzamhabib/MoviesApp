package com.az.moviesapp.core.data.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Consume error and return readable list of API errors
 * @param errorBody
 */
fun consumeError(errorBody: String): List<ApiError> {
    if (errorBody.isEmpty()) listOf(
        ApiError(
            field = ErrorField.UNKNOWN,
            cause = "Empty error description"
        )
    )

    val objectsList: Type = object : TypeToken<JsonObject>() {}.type
    val gson = Gson()
    return try {
        val errorResponse: JsonObject = gson.fromJson(errorBody, objectsList)
        return parseError(errorResponse)
    } catch (ecp: Exception) {
        listOf(ApiError(field = ErrorField.UNKNOWN, cause = "Can`t parse Error"))
    }
}

/**
 * Parse JsonObject to ApiError list
 * @param errorBody income json object
 */
private fun parseError(errorBody: JsonObject): List<ApiError> {
    val errorMap = mutableMapOf<String, String>()
    val gson = Gson()

    for ((field, cause) in errorBody.entrySet()) {
        try {
            val strCause = gson.fromJson(cause, JsonArray::class.java)
                .first()
                .toString()
                .replace("\"", "")

            errorMap[field] = strCause
        } catch (excp: Exception) {
            Log.d("Enaya_ApiError", "Can`t parse json, cause\t${excp.localizedMessage}")
        }
    }
    return parseMapToErrors(errorMap)
}


private fun parseMapToErrors(errorsInMap: MutableMap<String, String>): List<ApiError> {
    return errorsInMap.map { entry ->
        ApiError(
            originField = entry.key,
            field = ErrorField.parseStr(entry.key),
            cause = entry.value,
            causeType = ErrorCauseType.parseStr(entry.value)
        )
    }.toList()
}


enum class ErrorCauseType(private val cause: List<String>) {
    EMPTY(listOf("empty", "blank")),
    TO_SHORT(listOf("short", "length")),
    VALIDITY(listOf("valid", "correct", "used")),
    UNKNOWN(listOf());

    companion object {
        fun parseStr(strCause: String): ErrorCauseType {
            try {
                values().forEach {
                    it.cause.forEach { keyWord -> if (strCause.contains(keyWord, true)) return it }
                }
            } catch (exp: Exception) {
                println("Exception\t${exp.localizedMessage}")
            }
            return UNKNOWN
        }
    }
}

enum class ErrorField {
    EMAIL, PHONE, CIVIL_ID, PASSWORD, UNKNOWN;

    companion object {
        fun parseStr(strField: String): ErrorField {
            values().forEach { if (strField.contains(it.name, true)) return it }
            return UNKNOWN
        }
    }
}

data class ApiError(
    val field: ErrorField? = null,
    val originField: String? = null,
    val cause: String? = null,
    val causeType: ErrorCauseType? = null
) {
    override fun toString(): String {
        return "originField:$originField, field:$field, cause:$cause, causeType:$causeType"
    }


}