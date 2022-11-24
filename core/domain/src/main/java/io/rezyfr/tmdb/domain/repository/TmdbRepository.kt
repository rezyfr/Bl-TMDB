package io.rezyfr.tmdb.domain.repository

import io.rezyfr.tmdb.domain.model.MovieDomainModel
import io.rezyfr.tmdb.domain.utils.FetchData

interface TmdbRepository {
   suspend fun discoverMovies(page: Int): FetchData<List<MovieDomainModel>>
}