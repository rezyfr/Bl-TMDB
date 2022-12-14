package io.rezyfr.tmdb.domain.repository

import androidx.paging.PagingData
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.model.MovieDomainModel
import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.flow.Flow

interface TmdbRepository {
    fun discoverMovies(): Flow<PagingData<MovieDomainModel>>
    suspend fun getMovieDetail(movieId: Int) : FetchData<MovieDetailDomainModel>
}