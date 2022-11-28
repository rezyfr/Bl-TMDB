package io.rezyfr.tmdb.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import io.rezyfr.tmdb.databinding.ViewGenreBinding
import io.rezyfr.tmdb.utils.setVisibleOrGone

class GenreView constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ViewGenreBinding

    init {
        binding = ViewGenreBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    fun setView(genre: String, shouldShowSeparator: Boolean) {
        binding.tvGenre.text = genre
        binding.ivOval.setVisibleOrGone(shouldShowSeparator)
    }
}