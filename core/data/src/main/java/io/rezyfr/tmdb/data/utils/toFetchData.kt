package io.rezyfr.tmdb.data.utils

import io.rezyfr.tmdb.data.response.ErrorResponse
import io.rezyfr.tmdb.data.response.NetworkResponse
import io.rezyfr.tmdb.domain.utils.FetchData

fun <T> NetworkResponse<T, ErrorResponse>.toFetchData(): FetchData<T> {
    return when (this) {
        is NetworkResponse.Success -> FetchData.Success(body)
        is NetworkResponse.ServerError -> FetchData.Error(
            body?.message.orEmpty(),
            body?.code,
        )
        is NetworkResponse.NetworkError -> FetchData.Error(error.message.orEmpty())
        is NetworkResponse.UnknownError -> FetchData.Error(error.message.orEmpty(), code)
    }
}

fun <T, A> NetworkResponse<T, ErrorResponse>.toFetchDataDomain(toDomain: (T) -> A): FetchData<A> {
    return when (this) {
        is NetworkResponse.Success -> FetchData.Success(toDomain(body))
        is NetworkResponse.ServerError -> FetchData.Error(
            body?.message.orEmpty(),
            body?.code
        )
        is NetworkResponse.NetworkError -> FetchData.Error(error.message.orEmpty())
        is NetworkResponse.UnknownError -> FetchData.Error(error.message.orEmpty(), code)
    }
}

fun <T> NetworkResponse<T, ErrorResponse>.toFetchDataError(): FetchData<Any> {
    return when (this) {
        is NetworkResponse.ServerError -> FetchData.Error(body?.message.orEmpty())
        is NetworkResponse.NetworkError -> FetchData.Error(error.message.orEmpty())
        is NetworkResponse.UnknownError -> FetchData.Error(error.message.orEmpty())
        else -> FetchData.Empty
    }
}