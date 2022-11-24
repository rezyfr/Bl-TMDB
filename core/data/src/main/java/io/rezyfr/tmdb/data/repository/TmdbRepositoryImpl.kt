package io.rezyfr.tmdb.data.repository

import io.rezyfr.tmdb.data.service.TmdbService
import io.rezyfr.tmdb.data.utils.toFetchDataDomain
import io.rezyfr.tmdb.domain.model.MovieDomainModel
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import io.rezyfr.tmdb.domain.utils.FetchData

class TmdbRepositoryImpl constructor(
    private val service: TmdbService
): TmdbRepository {

    override suspend fun discoverMovies(page: Int): FetchData<List<MovieDomainModel>> {
        return service.discoverMovie(page).toFetchDataDomain {
            it.map { it.toDomain() }
        }
    }
}