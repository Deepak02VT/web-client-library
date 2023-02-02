package com.vtn.android.reusable.sample.webconnectsample.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater

abstract class BaseRecycleViewAdapter<B : ViewDataBinding?> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mShouldLoadMore = true
    private var mIsLoading = false
    private var mIsReversedScrolling = false
    private var mLoadMoreListener: OnLoadMoreListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onCreateView(parent, viewType)
    }

    fun ismIsLoading(): Boolean {
        return mIsLoading
    }

    /**
     * Subclasses should override this method, to actually bind the view data,
     * but always call `super.onBindViewHolder(holder, position)`
     * to enable the adapter to calculate whether the load more callback should be invoked
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mShouldLoadMore && !mIsLoading) {
            val threshold = visibleThreshold
            val hasReachedThreshold =
                if (mIsReversedScrolling) position <= threshold else position >= count - threshold
            if (hasReachedThreshold) {
                mIsLoading = true
                if (mLoadMoreListener != null) {
                    mLoadMoreListener!!.onLoadMore()
                }
            }
        }
        val viewHolder: BaseViewHolder = holder as BaseRecycleViewAdapter<B>.BaseViewHolder
        bindData(position, viewHolder)
        try {
            // not in use right now
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected abstract fun bindData(position: Int, baseViewHolder: BaseViewHolder?)
    override fun getItemCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoadingView(position)) {
            VIEW_TYPE_LOADING
        } else {
            val viewType = getViewType(position)
            if (viewType == VIEW_TYPE_LOADING) {
                throw IndexOutOfBoundsException("0 index is reserved for the loading view")
            } else {
                viewType
            }
        }
    }

    /**
     * check weather loading is in progress or not.
     *
     * @param position
     * @return
     */
    private fun isLoadingView(position: Int): Boolean {
        // For reversed scrolling, the loading view is always the top one
        val loadingViewPosition = if (mIsReversedScrolling) 0 else count
        return position == loadingViewPosition && mShouldLoadMore
    }

    /**
     * Set as false when you don't want the recycler view to load more data.
     * This will also remove the loading view
     */
    fun setShouldLoadMore(shouldLoadMore: Boolean) {
        mShouldLoadMore = shouldLoadMore
    }

    /**
     * Set as true if you want the endless scrolling to be as the user scrolls
     * to the top of the list, instead of bottom
     */
    fun setIsReversedScrolling(reversed: Boolean) {
        mIsReversedScrolling = reversed
    }

    /**
     * Registers a callback to be notified when there is a need to load more data
     */
    fun setOnLoadMoreListener(listener: OnLoadMoreListener?) {
        mLoadMoreListener = listener
    }

    /**
     * This informs the adapter that `itemCount` more data has been loaded,
     * starting from `positionStart`
     *
     *
     * This also calls `notifyItemRangeInserted(int, int)`,
     * so the implementing class only needs to call this method
     *
     * @param positionStart Position of the first item that was inserted
     * @param itemCount     Number of items inserted
     */
    fun moreDataLoaded(positionStart: Int, itemCount: Int) {
        try {
            mIsLoading = false
            notifyItemRemoved(positionStart) // remove the loading view
            notifyItemRangeInserted(positionStart, itemCount)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun removeLoadingView(positionStart: Int) {
        mIsLoading = false
        notifyItemRemoved(positionStart) // remove the loading view
    }

    /**
     * Returns the number of scrollable items that should be left (threshold) in the
     * list before `OnLoadMoreListener` will be called
     *
     *
     * You can override this to return a preffered threshold,
     * or leave it to use the default
     *
     * @return integer threshold
     */
    private val visibleThreshold: Int
        private get() = 5

    /**
     * The count of the number of items in the list. This does not include the loading item
     *
     * @return number of items in list
     */
    protected abstract val count: Int

    /**
     * Return the view type of the item at `position` for the purposes
     * of view recycling.
     *
     *
     *
     * 0 index is reserved for the loading view. So this function cannot return 0.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * `position`. Type codes need not be contiguous.
     */
    private fun getViewType(position: Int): Int {
        return 1
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item. This is the same as the onCreateViewHolder method in RecyclerView.Adapter,
     * except that it internally detects and handles the creation on the loading footer
     *
     * @param parent
     * @param viewType
     * @return
     */
    private fun onCreateView(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binder: B = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), inflateLayout, parent, false
        )
        return BaseViewHolder(binder)
    }

    /**
     * @return layout of inflator.
     */
    protected abstract val inflateLayout: Int

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    /**
     * ViewHolder class for the adapter,We are using binding here ,so a single ViewHolder can be used for all.
     */
    inner class BaseViewHolder(val binding: B) : RecyclerView.ViewHolder(
        binding!!.root
    )

    companion object {
        private const val VIEW_TYPE_LOADING = 0
    }
}