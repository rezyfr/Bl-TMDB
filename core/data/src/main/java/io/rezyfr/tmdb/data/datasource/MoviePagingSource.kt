package io.rezyfr.tmdb.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.rezyfr.tmdb.data.NetworkConfig
import io.rezyfr.tmdb.data.response.NetworkResponse
import io.rezyfr.tmdb.data.service.TmdbService
import io.rezyfr.tmdb.domain.model.MovieDomainModel

class MoviePagingSource(
    private val service: TmdbService,
    private val networkConfig: NetworkConfig
) : PagingSource<Int, MovieDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDomainModel>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomainModel> {
        return try {
            val nextPage = params.key ?: 1
            val response = service.discoverMovie(nextPage)
            when (response) {
                is NetworkResponse.Success -> {
                    val result = response.body
                    LoadResult.Page(
                        data = result.results?.map { it.toDomain() }.orEmpty(),
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = if (nextPage < result.totalPages!!) result.page?.plus(1) else null
                    )
                }
                is NetworkResponse.NetworkError -> {
                    LoadResult.Error(Throwable(response.error))
                }
                is NetworkResponse.ServerError -> {
                    LoadResult.Error(Throwable(response.body?.message))
                }
                is NetworkResponse.UnknownError -> {
                    LoadResult.Error(response.error)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}