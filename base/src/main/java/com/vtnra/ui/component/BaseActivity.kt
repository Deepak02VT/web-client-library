package com.vtnra.ui.component

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.vtnra.ui.viewmodel.BaseViewModel

abstract class BaseActivity<T : ViewDataBinding?, V : BaseViewModel<*>?> : AppCompatActivity(),
    BaseFragment.Callback {
    var viewDataBinding: T? = null
        private set

    var viewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int
    override fun onFragmentAttached() {
        // override method to use with Fragments on child Activities
    }

    override fun onFragmentDetached(tag: String?) {
        // override method to use with Fragments on child Activities
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding!!.setVariable(bindingVariable, viewModel)
        viewDataBinding!!.executePendingBindings()
    }
}
