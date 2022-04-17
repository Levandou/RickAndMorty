package com.velagissellint.presentation.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.velagissellint.presentation.R

class DividerItemDecoration(context: Context?) : RecyclerView.ItemDecoration() {
    private var divider: Drawable? = null

    init {
        divider = context?.let { ContextCompat.getDrawable(it, R.drawable.divider) }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom: Int? = divider?.intrinsicHeight?.let { top.plus(it) }
            bottom?.let { divider?.setBounds(left, top, right, it) }
            divider?.draw(c)
        }
    }
}
