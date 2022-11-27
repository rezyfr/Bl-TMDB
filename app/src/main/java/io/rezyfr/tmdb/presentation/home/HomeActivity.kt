package io.rezyfr.tmdb.presentation.home

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import io.rezyfr.tmdb.R
import io.rezyfr.tmdb.base.BaseActivity
import io.rezyfr.tmdb.databinding.ActivityHomeBinding
import io.rezyfr.tmdb.presentation.detail.DetailActivity
import io.rezyfr.tmdb.presentation.home.adapter.MovieDiscoverAdapter
import io.rezyfr.tmdb.presentation.home.adapter.MovieLoadStateAdapter
import io.rezyfr.tmdb.utils.*
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(),
    MovieDiscoverAdapter.MovieDiscoverListener {
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { MovieDiscoverAdapter(this) }
    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun setupView() {
        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = this@HomeActivity.adapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter {
                    this@HomeActivity.adapter.retry()
                }
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.movies.collectLatest {
                adapter.submitData(it)
                Timber.d(it.toString())
            }
        }

        binding.btnRetry.setOnClickListener {
            adapter.refresh()
        }

        binding.tvHeader.text = buildSpanned {
            append("TheMovie")
            append("DB", foregroundColor(getColor(R.color.yellow_05)))
        }

        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {

                binding.btnRetry.gone()

                // Show ProgressBar
                binding.pbLoading.visible()
            } else {
                // Hide ProgressBar
                binding.pbLoading.gone()

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        binding.btnRetry.visible()
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onMovieClicked(id: Int) {
        val intent = DetailActivity.create(this, id)
        startActivity(intent)
    }
}