package io.rezyfr.tmdb.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rezyfr.tmdb.domain.usecase.DiscoverMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
     discoverMoviesUseCase: DiscoverMoviesUseCase
) : ViewModel() {
    val movies = discoverMoviesUseCase.invoke()

}