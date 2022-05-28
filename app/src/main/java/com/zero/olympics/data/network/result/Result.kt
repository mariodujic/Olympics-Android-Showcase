package com.zero.olympics.data.network.result

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val value: T) : Result<T>()
    object Empty : Result<Nothing>()
    data class Error(val code: Int? = null) : Result<Nothing>()
}