package io.rezyfr.tmdb.data.response
data class ErrorResponse(
    val status: String? = null,
    val code: Int? = null,
    val message: String? = null,
    val warning: String? = null
)