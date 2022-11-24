package io.rezyfr.tmdb.domain.usecase

import io.rezyfr.tmdb.domain.repository.TmdbRepository

class DiscoverMoviesUseCase constructor (
    private val repo: TmdbRepository
) {
    suspend operator fun invoke(page: Int) =
        repo.discoverMovies(page)
}