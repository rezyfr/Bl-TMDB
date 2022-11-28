package io.rezyfr.tmdb.data.response.feature

import com.google.gson.annotations.SerializedName
import io.rezyfr.tmdb.domain.model.GenreModel
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.model.MovieDomainModel

data class MovieDetailResponse(
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genres")
    val genres: List<GenreModel>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
) {
    fun toDomain() = MovieDetailDomainModel(
        originalLanguage = originalLanguage.orEmpty(),
        title = title.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        genres = genres.orEmpty(),
        id = id ?: 0,
        voteCount = voteCount ?: 0,
        overview = overview.orEmpty(),
        runtime = runtime ?: 0,
        posterPath = posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
    )
}