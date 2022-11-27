package io.rezyfr.tmdb.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class GetMovieDetailUseCaseTest {
    private lateinit var useCase: GetMovieDetailUseCase

    @Mock
    private lateinit var repository: TmdbRepository

    @Before
    fun setUp() {
        useCase = GetMovieDetailUseCase(repository, Dispatchers.IO)
    }

    @Test
    fun `when given movieId, should emit loading then success`(): Unit = runBlocking {
        val movieId = 1
        val movie = mock<MovieDetailDomainModel>()
        whenever(repository.getMovieDetail(movieId)).thenReturn(FetchData.Success(movie))
        val result = useCase.invoke(movieId).toList()
        verify(repository, times(1)).getMovieDetail(movieId)
        assertEquals(result, listOf(FetchData.Loading, FetchData.Success(movie)))
    }

    @Test
    fun `when given wrong movieId, should emit loading then error`(): Unit = runBlocking {
        val movieId = 0
        val error = FetchData.Error("", 404)
        whenever(repository.getMovieDetail(movieId)).thenReturn(error)
        val result = useCase.invoke(movieId).toList()
        verify(repository, times(1)).getMovieDetail(movieId)
        assertEquals(result, listOf(FetchData.Loading, error))
    }
}