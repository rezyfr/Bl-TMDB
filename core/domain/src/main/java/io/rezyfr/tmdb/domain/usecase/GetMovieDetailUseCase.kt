package io.rezyfr.tmdb.domain.usecase

import io.rezyfr.tmdb.domain.base.BaseUseCaseFlow
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class GetMovieDetailUseCase constructor(
    private val repo: TmdbRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCaseFlow<Int, MovieDetailDomainModel>(dispatcher) {

    override fun execute(param: Int): Flow<FetchData<MovieDetailDomainModel>> {
        return flow {
            emit(FetchData.Loading)
            emit(repo.getMovieDetail(param))
        }
    }
}