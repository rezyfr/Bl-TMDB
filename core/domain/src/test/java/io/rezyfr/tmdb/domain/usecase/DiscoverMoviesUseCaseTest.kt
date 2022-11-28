package io.rezyfr.tmdb.domain.usecase

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.model.MovieDomainModel
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DiscoverMoviesUseCaseTest {
    private lateinit var useCase: DiscoverMoviesUseCase

    @Mock
    private lateinit var repository: TmdbRepository

    @Before
    fun setUp() {
        useCase = DiscoverMoviesUseCase(repository)
    }

    @Test
    fun `when invoked, should fetch movies from repository`(): Unit = runBlocking {
        val data = mock<Flow<PagingData<MovieDomainModel>>>()
        whenever(repository.discoverMovies()).thenReturn(data)
        useCase.invoke()
        verify(repository, times(1)).discoverMovies()
    }
}