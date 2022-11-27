package io.rezyfr.tmdb.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.usecase.GetMovieDetailUseCase
import io.rezyfr.tmdb.domain.utils.FetchData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {
    private val _detail = MutableLiveData<FetchData<MovieDetailDomainModel>>()
    val detail: LiveData<FetchData<MovieDetailDomainModel>> get() = _detail

    fun init(id: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.invoke(id).collectLatest {
                _detail.value = it
            }
        }
    }
}