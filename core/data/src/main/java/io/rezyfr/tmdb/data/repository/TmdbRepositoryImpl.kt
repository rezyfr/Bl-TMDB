package io.rezyfr.tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.rezyfr.tmdb.data.NetworkConfig
import io.rezyfr.tmdb.data.datasource.MoviePagingSource
import io.rezyfr.tmdb.data.service.TmdbService
import io.rezyfr.tmdb.data.utils.toFetchDataDomain
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.model.MovieDomainModel
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.flow.Flow

class TmdbRepositoryImpl constructor(
    private val service: TmdbService,
) : TmdbRepository {

    override fun discoverMovies(): Flow<PagingData<MovieDomainModel>> {
        return Pager(PagingConfig(20)) {
            MoviePagingSource(service)
        }.flow
    }

    override suspend fun getMovieDetail(movieId: Int): FetchData<MovieDetailDomainModel> {
        return service.getMovieDetail(movieId).toFetchDataDomain { it.toDomain() }
    }
}

