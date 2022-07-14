package com.vtnra.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView

class BaseCardView : CardView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
       // not in use right now
    }
}
