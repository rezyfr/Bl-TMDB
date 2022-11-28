package io.rezyfr.tmdb.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import io.rezyfr.tmdb.databinding.ItemLoadingStateBinding
import io.rezyfr.tmdb.utils.setVisibleOrGone

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class LoadStateViewHolder(private val binding: ItemLoadingStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            with(binding) {
                pbLoad.setVisibleOrGone(loadState is LoadState.Loading)
                btnRetry.setVisibleOrGone(loadState !is LoadState.Loading)
                tvErrorMessage.setVisibleOrGone(loadState !is LoadState.Loading)

                if (loadState is LoadState.Error) {
                    tvErrorMessage.text = loadState.error.localizedMessage
                }
                btnRetry.setOnClickListener {
                    retry.invoke()
                }
            }
        }
    }
}