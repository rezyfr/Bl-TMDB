package io.rezyfr.tmdb.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.rezyfr.tmdb.BuildConfig
import io.rezyfr.tmdb.databinding.ItemMovieHomeBinding
import io.rezyfr.tmdb.domain.model.MovieDomainModel
import timber.log.Timber

class MovieDiscoverAdapter(
    private val listener: MovieDiscoverListener
) : PagingDataAdapter<MovieDomainModel, MovieDiscoverAdapter.MovieViewHolder>(
        MovieModelComparator
    ) {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Timber.d("Movie: ${getItem(position)}")
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieHomeBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class MovieViewHolder(private val binding: ItemMovieHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieDomainModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BACKDROP_URL + data.backdropPath)
                    .into(ivBackdrop)

                tvDate.text = data.releaseDate
                tvTitle.text = data.title
                tvOverview.text = data.overview
                ratingBar.rating = data.voteAverage.div(2).toFloat()
                root.setOnClickListener {
                    listener.onMovieClicked(data.id)
                }
            }
        }
    }

    interface MovieDiscoverListener {
        fun onMovieClicked(id: Int)
    }

    companion object {
        private val MovieModelComparator = object : DiffUtil.ItemCallback<MovieDomainModel>() {
            override fun areItemsTheSame(
                oldItem: MovieDomainModel,
                newItem: MovieDomainModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieDomainModel,
                newItem: MovieDomainModel
            ): Boolean =
                oldItem == newItem
        }
    }
}