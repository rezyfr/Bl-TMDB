package io.rezyfr.tmdb.data.response.feature

import com.google.gson.annotations.SerializedName
import io.rezyfr.tmdb.domain.model.MovieDomainModel

data class MovieResponse(
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) {
    fun toDomain() = MovieDomainModel(
        overview = overview.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        title = title.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        posterPath = posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        id = id ?: 0,
        voteCount = voteCount ?: 0,
    )
}
