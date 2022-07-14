package com.vtnra.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerViewEmptySupport : RecyclerView {
    private var emptyView: View? = null

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        val emptyObserver: AdapterDataObserver = object : AdapterDataObserver() {
            override fun onChanged() {
                val adapter = getAdapter()
                if (adapter != null && emptyView != null) {
                    if (adapter.itemCount == 0) {
                        emptyView!!.visibility = VISIBLE
                    } else {
                        emptyView!!.visibility = GONE
                        this@BaseRecyclerViewEmptySupport.visibility = VISIBLE
                    }
                } else {
                    this@BaseRecyclerViewEmptySupport.visibility = VISIBLE
                }
            }
        }
        adapter?.registerAdapterDataObserver(emptyObserver)
        emptyObserver.onChanged()
    }

    fun setEmptyView(emptyView: View?) {
        this.emptyView = emptyView
    }
}