package io.rezyfr.tmdb.domain.model


data class DiscoverDomainModel(
    val page: Int,
    val results: List<MovieDomainModel>,
    val totalPages: Int,
    val totalResults: Int
)