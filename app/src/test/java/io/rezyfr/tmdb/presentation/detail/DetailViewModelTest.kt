package io.rezyfr.tmdb.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.usecase.GetMovieDetailUseCase
import io.rezyfr.tmdb.domain.utils.FetchData
import io.rezyfr.tmdb.utils.MainCoroutinesRule
import io.rezyfr.tmdb.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutinesRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var detailUseCase: GetMovieDetailUseCase
    lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel(detailUseCase)
    }

    @Test
    fun `init should invoke usecase and post value, if success should return fetch data success`() : Unit = coroutineRule.runBlockingTest {
        val movieId = 1
        val movie = mock<MovieDetailDomainModel>()
        whenever(detailUseCase.invoke(movieId)).thenReturn(flowOf(FetchData.Success(movie)))
        viewModel.init(movieId)
        assertEquals(viewModel.detail.getOrAwaitValue(), FetchData.Success(movie))
    }

    @Test
    fun `init should invoke usecase and post value, if error should return fetch data error`() : Unit = coroutineRule.runBlockingTest {
        val movieId = 1
        whenever(detailUseCase.invoke(movieId)).thenReturn(flowOf(FetchData.Error("error")))
        viewModel.init(movieId)
        assertEquals(viewModel.detail.getOrAwaitValue(), FetchData.Error("error"))
    }
}
