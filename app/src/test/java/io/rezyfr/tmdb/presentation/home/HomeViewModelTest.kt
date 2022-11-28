package io.rezyfr.tmdb.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.rezyfr.tmdb.data.response.feature.MovieResponse
import io.rezyfr.tmdb.domain.model.MovieDomainModel
import io.rezyfr.tmdb.domain.usecase.DiscoverMoviesUseCase
import io.rezyfr.tmdb.utils.MainCoroutinesRule
import io.rezyfr.tmdb.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutinesRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var useCase: DiscoverMoviesUseCase
    lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun `if usecase return success, movies value should be paging data of movies`(): Unit =
        coroutineRule.runBlockingTest {
            val movie = PagingData.from(listOf(MovieDomainModel(
                "",
                "",
                "",
                "",
                "",
                "",
                0.0,
                0,
                0,
            )))
            whenever(useCase.invoke()).thenReturn(flowOf(movie))
            viewModel.init()
            assertEquals(viewModel.movies.getOrAwaitValue(), movie)
        }
}