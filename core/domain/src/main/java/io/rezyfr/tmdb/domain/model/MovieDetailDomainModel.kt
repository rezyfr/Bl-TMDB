package io.rezyfr.tmdb.domain.model

data class MovieDetailDomainModel(
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val genres: List<GenreModel>,
    val id: Int,
    val voteCount: Int,
    val overview: String,
    val runtime: Int,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
)