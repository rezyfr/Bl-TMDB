package io.rezyfr.tmdb.data.service

import io.rezyfr.tmdb.data.response.ErrorResponse
import io.rezyfr.tmdb.data.response.NetworkResponse
import io.rezyfr.tmdb.data.response.feature.DiscoverResponse
import io.rezyfr.tmdb.data.response.feature.MovieDetailResponse
import io.rezyfr.tmdb.data.response.feature.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("page") page: Int
    ): NetworkResponse<DiscoverResponse, ErrorResponse>


    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): NetworkResponse<MovieDetailResponse, ErrorResponse>
}