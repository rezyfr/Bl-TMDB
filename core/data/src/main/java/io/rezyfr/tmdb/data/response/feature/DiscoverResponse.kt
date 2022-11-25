package io.rezyfr.tmdb.data.response.feature


import com.google.gson.annotations.SerializedName

data class DiscoverResponse (
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieResponse>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)