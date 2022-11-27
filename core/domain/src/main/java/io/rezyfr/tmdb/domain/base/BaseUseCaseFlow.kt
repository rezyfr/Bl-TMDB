package io.rezyfr.tmdb.domain.base

import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCaseFlow<in P, out R> constructor(private val dispatcher: CoroutineDispatcher) {
    operator fun invoke(param: P): Flow<FetchData<R>> = execute(param).catch {
        emit(FetchData.Error(it.message.orEmpty()))
    }.flowOn(dispatcher)

    protected abstract fun execute(param: P): Flow<FetchData<R>>
}