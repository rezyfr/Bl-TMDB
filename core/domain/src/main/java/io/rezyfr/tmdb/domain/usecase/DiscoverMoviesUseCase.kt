package io.rezyfr.tmdb.domain.usecase

import io.rezyfr.tmdb.domain.repository.TmdbRepository

class DiscoverMoviesUseCase constructor (
    private val repo: TmdbRepository
) {
    operator fun invoke() = repo.discoverMovies()
}