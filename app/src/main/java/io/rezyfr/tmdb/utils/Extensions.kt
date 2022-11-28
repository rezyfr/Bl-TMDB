package io.rezyfr.tmdb.utils

import android.view.View
import androidx.constraintlayout.widget.Group
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow


fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

/**
 * source: https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp?lq=1
 */

fun Int.formatToK(): String {
    if (this < 1000) return toString()
    val exp = ln(this.toDouble()).div(ln(1000.0)).toInt()
    return String.format("%.2f %c", this.div(1000.0.pow(exp)), "kMGTPE"[exp - 1])
}

fun Int.formatToHourMinutes(): String {
    val hours = this.div(60)
    val minutes = this % 60
    return String.format("%dh %02dm", hours, minutes)
}

fun parseDateQuery(dateString: String): String {
    val locale = Locale("en", "en")
    return try {
        val displayFormat = SimpleDateFormat("dd MMMM yyyy", locale)
        SimpleDateFormat("yyyy-MM-dd", locale).parse(dateString)?.let { date ->
            displayFormat.format(date)
        } ?: ""
    } catch (e: ParseException) {
        e.printStackTrace()
        ""
    }
}

fun Group.setAllVisibleOrGone(visible: Boolean) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setVisibleOrGone(visible)
    }
}