package io.rezyfr.themoviedb.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    @GET("movie/")
    suspend fun discoverMovie(
        @Query("page") page: Int
    ): List<MovieResponse>


//    @GET("movie/{movie_id}")
//    suspend fun getMovieDetail(
//        @Path("movie_id") movieId: Int
//    ): MovieDetailResponse
}