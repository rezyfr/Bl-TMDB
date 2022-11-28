package io.rezyfr.tmdb.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rezyfr.tmdb.domain.model.MovieDomainModel
import io.rezyfr.tmdb.domain.usecase.DiscoverMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
     private val discoverMoviesUseCase: DiscoverMoviesUseCase
) : ViewModel() {
    private val _movies = MutableLiveData<PagingData<MovieDomainModel>>(PagingData.empty())
    val movies: LiveData<PagingData<MovieDomainModel>> get() = _movies

    fun init() {
        viewModelScope.launch {
            discoverMoviesUseCase.invoke().collect {
                _movies.value = it
            }
        }
    }
}