package com.zero.olympics.data.network.mapper

import com.zero.olympics.data.network.result.Result
import retrofit2.Response
import javax.inject.Inject

class NetworkResponseMapper @Inject constructor() {

    suspend operator fun <C> invoke(response: suspend () -> Response<C>): Result<C> = try {
        response().let {
            if (it.isSuccessful) {
                it.body()?.let { body ->
                    Result.Success(body as C)
                } ?: Result.Empty
            } else {
                Result.Error(it.code())
            }
        }
    } catch (e: Exception) {
        Result.Error()
    }
}