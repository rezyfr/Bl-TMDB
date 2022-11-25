package io.rezyfr.tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.rezyfr.tmdb.data.NetworkConfig
import io.rezyfr.tmdb.data.datasource.MoviePagingSource
import io.rezyfr.tmdb.data.service.TmdbService
import io.rezyfr.tmdb.domain.model.MovieDomainModel
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import kotlinx.coroutines.flow.Flow

class TmdbRepositoryImpl constructor(
    private val service: TmdbService,
    private val networkConfig: NetworkConfig
) : TmdbRepository {

    override fun discoverMovies(): Flow<PagingData<MovieDomainModel>> {
        return Pager(PagingConfig(20)) {
            MoviePagingSource(service, networkConfig)
        }.flow
    }
}