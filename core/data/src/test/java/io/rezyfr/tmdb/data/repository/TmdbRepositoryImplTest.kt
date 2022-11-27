package io.rezyfr.tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.rezyfr.tmdb.data.datasource.MoviePagingSource
import io.rezyfr.tmdb.data.response.ErrorResponse
import io.rezyfr.tmdb.data.response.NetworkResponse
import io.rezyfr.tmdb.data.response.feature.MovieDetailResponse
import io.rezyfr.tmdb.data.service.TmdbService
import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TmdbRepositoryImplTest {
    @Mock
    lateinit var service: TmdbService
    lateinit var repository: TmdbRepositoryImpl

    @Before
    fun setup() {
        repository = TmdbRepositoryImpl(service)
    }

    @Test
    fun `when getMovieDetail is success, should return fetch data success`() : Unit = runBlocking {
        val movieId = 1
        val movie = MovieDetailResponse(id = movieId)
        whenever(service.getMovieDetail(movieId)).thenReturn(NetworkResponse.Success(body = movie, code = 200))
        val response = repository.getMovieDetail(movieId)
        verify(service, times(1)).getMovieDetail(movieId)
        assert(response == FetchData.Success(movie.toDomain()))
    }

    @Test
    fun `when getMovieDetail is network error, should return fetch data error`() : Unit = runBlocking {
        val movieId = 1
        whenever(service.getMovieDetail(movieId)).thenReturn(NetworkResponse.NetworkError(
            IOException()
        ))
        val response = repository.getMovieDetail(movieId)
        verify(service, times(1)).getMovieDetail(movieId)
        assert(response == FetchData.Error(""))
    }

    @Test
    fun `when getMovieDetail is server error, should return fetch data error`() : Unit = runBlocking {
        val movieId = 1
        whenever(service.getMovieDetail(movieId)).thenReturn(NetworkResponse.ServerError(
            ErrorResponse(), 404
        ))
        val response = repository.getMovieDetail(movieId)
        verify(service, times(1)).getMovieDetail(movieId)
        assert(response == FetchData.Error(""))
    }

    @Test
    fun `when getMovieDetail is unknown error, should return fetch data error`() : Unit = runBlocking {
        val movieId = 1
        whenever(service.getMovieDetail(movieId)).thenReturn(NetworkResponse.UnknownError(
            IOException()
        ))
        val response = repository.getMovieDetail(movieId)
        verify(service, times(1)).getMovieDetail(movieId)
        assert(response == FetchData.Error(""))
    }

    @Test
    fun `when discoverMovies triggered, should not return null`() : Unit = runBlocking {
        val response = repository.discoverMovies()
        assertNotNull(response)
    }
}