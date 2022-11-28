package io.rezyfr.tmdb.presentation.detail

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import io.rezyfr.tmdb.R
import io.rezyfr.tmdb.base.BaseActivity
import io.rezyfr.tmdb.data.NetworkConfig
import io.rezyfr.tmdb.databinding.ActivityDetailBinding
import io.rezyfr.tmdb.domain.model.MovieDetailDomainModel
import io.rezyfr.tmdb.domain.utils.FetchData
import io.rezyfr.tmdb.presentation.customview.GenreView
import io.rezyfr.tmdb.utils.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    private val viewModel: DetailViewModel by viewModels()
    @Inject
    lateinit var networkConfig: NetworkConfig
    override fun getViewBinding(): ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

    override fun setupView() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnRetry.setOnClickListener {
            fetchData()
        }
        viewModel.detail.observe(this) {
            binding.shimmerGroup.setAllVisibleOrGone(it is FetchData.Loading)
            when(it) {
                is FetchData.Success -> {
                    stopShimmer()
                    loadMovieIntoView(it.data)
                }
                is FetchData.Error -> {
                    stopShimmer()
                    binding.btnRetry.visible()
                }
                is FetchData.Loading -> {
                    startShimmer()
                }
                else -> {
                    //No Operation
                }
            }
        }
        fetchData()
    }

    private fun loadMovieIntoView(movie: MovieDetailDomainModel) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(networkConfig.imageUrl() + movie.posterPath)
                .into(ivPoster)
            tvTitle.text = movie.title
            ratingBar.setVisibleOrGone(movie.voteAverage > 0)
            ratingBar.rating = movie.voteAverage.div(2).toFloat()
            tvVoteCount.text = getString(R.string.label_review, movie.voteCount.formatToK())
            tvDuration.text = movie.runtime.formatToHourMinutes()
            tvDate.text = parseDateQuery(movie.releaseDate)
            tvOverview.text = movie.overview
            btnRetry.gone()
            movie.genres.take(3).forEachIndexed { index, genre ->
                GenreView(this@DetailActivity).apply {
                    setView(genre.name, index != movie.genres.lastIndex)
                    layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                        setMargins(0,0,16,0)
                    }
                    containerGenre.addView(this)
                }
            }
        }
    }

    private fun fetchData() {
        val movieId = intent.getIntExtra(ID,0)
        viewModel.init(movieId)
    }

    private fun startShimmer() {
        binding.shimmerGroup.apply {
            referencedIds.forEach { id ->
                rootView.findViewById<ShimmerFrameLayout>(id).startShimmerAnimation()
            }
        }
    }

    private fun stopShimmer() {
        binding.shimmerGroup.apply {
            referencedIds.forEach { id ->
                rootView.findViewById<ShimmerFrameLayout>(id).stopShimmerAnimation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startShimmer()
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