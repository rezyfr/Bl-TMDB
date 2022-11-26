package io.rezyfr.tmdb.domain.usecase

import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieDetailUseCase constructor(
    private val repo: TmdbRepository
) {
    suspend operator fun invoke(id: Int) : Flow<FetchData<MovieDetailDomainModel>>{
        return flow {
            emit(FetchData.Loading)
            val response = repo.getMovieDetail(id)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}