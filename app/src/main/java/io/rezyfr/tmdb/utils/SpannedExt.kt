package io.rezyfr.tmdb.utils

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan


inline fun buildSpanned(f: SpannableStringBuilder.() -> Unit): Spanned {
    return SpannableStringBuilder().apply(f)
}

fun SpannableStringBuilder.foregroundColor(color: Int): ForegroundColorSpan {
    return ForegroundColorSpan(color)
}

fun SpannableStringBuilder.append(text: CharSequence, vararg spans: Any) {
    val textLength = text.length
    append(text)
    spans.forEach { span ->
        setSpan(span, this.length - textLength, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

fun SpannableStringBuilder.append(text: CharSequence, span: Any) {
    val textLength = text.length
    append(text)
    setSpan(span, length - textLength, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
}

inline fun SpannableStringBuilder.append(span: Any, f: SpannableStringBuilder.() -> Unit) = apply {
    val start = length
    f()
    setSpan(span, start, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
}