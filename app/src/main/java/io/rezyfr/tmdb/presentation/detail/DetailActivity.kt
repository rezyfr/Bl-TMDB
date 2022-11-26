package io.rezyfr.tmdb.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.rezyfr.tmdb.base.BaseActivity
import io.rezyfr.tmdb.databinding.ActivityDetailBinding
import io.rezyfr.tmdb.domain.utils.FetchData

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    private val viewModel: DetailViewModel by viewModels()
    override fun getViewBinding(): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun setupView() {
        val movieId = intent.getIntExtra(ID,0)
        viewModel.init(movieId)
        viewModel.detail.observe(this) {
            when(it){
                is FetchData.Success -> {

                }
                is FetchData.Error -> {

                }
                is FetchData.Loading -> {

                }
                else -> {
                    //No Operation
                }
            }
        }
    }

    companion object {
        private const val ID = "ID"
        fun create(context: Context, movieId: Int) = Intent(
            context, DetailActivity::class.java
        ).apply {
            putExtra(ID, movieId)
        }
    }
}