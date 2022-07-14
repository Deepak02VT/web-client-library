package com.vtnra.ui.list

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

/**
 * A reusable divider for list items in Recyclerview.It will only work for grid Horizontal item listing.
 * Divider won't show for last item.
 * */
class GridItemDecorator(private val dividerDrawable: Drawable?) : RecyclerView.ItemDecoration() {

    private val rect = Rect()

    override fun onDraw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        if (recyclerView.layoutManager == null) {
            return
        }
        drawDivider(canvas, recyclerView)
    }

    private fun drawDivider(canvas: Canvas, recyclerView: RecyclerView) {
        canvas.save()
        val topPadding: Int
        val bottomPadding: Int

        if (recyclerView.clipToPadding) {
            topPadding = recyclerView.paddingTop
            bottomPadding = recyclerView.height - recyclerView.paddingBottom
            canvas.clipRect(
                recyclerView.paddingLeft, topPadding,
                recyclerView.width - recyclerView.paddingRight, bottomPadding
            )
        } else {
            topPadding = 0
            bottomPadding = recyclerView.height
        }

        var childCount = recyclerView.childCount
        if (recyclerView.layoutManager is GridLayoutManager) {
            childCount = (recyclerView.layoutManager as GridLayoutManager).spanCount
        }

        for (i in 0 until childCount - 1) {
            if (dividerDrawable != null) {
                val child = recyclerView.getChildAt(i) ?: return
                recyclerView.layoutManager?.getDecoratedBoundsWithMargins(child, rect)
                val right = rect.right + child.translationX.roundToInt()
                val left = right - dividerDrawable.intrinsicWidth
                dividerDrawable.setBounds(left, topPadding, right, bottomPadding)
                dividerDrawable.draw(canvas)
            }
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        rect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State
    ) {
        if (dividerDrawable != null) {
            rect.set(0, 0, dividerDrawable.intrinsicWidth, 0)
        }
    }
}
