package io.rezyfr.tmdb.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.whenever
import io.rezyfr.tmdb.data.response.NetworkResponse
import io.rezyfr.tmdb.data.response.feature.DiscoverResponse
import io.rezyfr.tmdb.data.response.feature.MovieResponse
import io.rezyfr.tmdb.data.service.TmdbService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class PagingSourceTest {
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    lateinit var source: MoviePagingSource
//
//    @Mock
//    private lateinit var service: TmdbService
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        source = MoviePagingSource(service)
//    }
//
//    @Test
//    fun `when source loaded is success, should return paging source`(): Unit = runBlockingTest {
//        whenever(service.discoverMovie(1)).thenReturn(NetworkResponse.Success(nextResponse,code = 200))
//        val expectedResult = PagingSource.LoadResult.Page(
//            data = nextResponse.results?.map { it.toDomain() }.orEmpty(),
//            prevKey = 1,
//            nextKey = 2
//        )
//        assertEquals(
//            expectedResult, source.load(
//                PagingSource.LoadParams.Append(
//                    key = 1,
//                    loadSize = 1,
//                    placeholdersEnabled = false,
//                ),
//            )
//        )
//    }
//
//    companion object {
//        val firstResponse = DiscoverResponse(
//            page = 1,
//            results = listOf(
//                MovieResponse(id = 1)
//            ),
//            totalPages = 10,
//            totalResults = 100
//        )
//        val nextResponse = DiscoverResponse(
//            page = 2,
//            results = listOf(
//                MovieResponse(id = 2)
//            ),
//            totalPages = 10,
//            totalResults = 100
//        )
//
//    }
}