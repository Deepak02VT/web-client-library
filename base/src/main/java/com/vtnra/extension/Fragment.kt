package com.vtnra.extension

import android.os.Build
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

object Fragment {

    fun AppCompatActivity.replaceFragment(
        fragment: Fragment,
        mainLayoutId: Int,
        tag: String = "TAG",
        inAnim: Int = -1,
        outAnim: Int = -1
    ) {
        val backStateName = if (tag == "TAG")
            fragment.javaClass.name
        else tag

        var isFragmentPopped = false
        try {
            isFragmentPopped =
                supportFragmentManager.popBackStackImmediate(backStateName, 0)
        } catch (e: IllegalStateException) {
            Log.d(tag, "Failed to replace fragment: ${e.message}")
        } catch (e: Exception) {
            Log.d(tag, "Failed to replace fragment: ${e.message}")
        }

        if (!isFragmentPopped && supportFragmentManager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            supportFragmentManager.inTransaction {
                val fragmentTransaction = replace(mainLayoutId, fragment)
                if (inAnim != -1 && outAnim != -1) {
                    fragmentTransaction.setCustomAnimations(inAnim, outAnim, inAnim, outAnim)
                }
                if (!backStateName.isNullOrEmpty()) fragmentTransaction.addToBackStack(backStateName)
                fragmentTransaction
            }
        }
    }

    fun AppCompatActivity.getCurrentFragment(): Fragment? {
        val fragmentManager = supportFragmentManager
        var fragmentTag: String? = ""

        if (fragmentManager.backStackEntryCount > 0)
            fragmentTag =
                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name

        return fragmentManager.findFragmentByTag(fragmentTag)
    }

    // Method to replace current fragment with required fragment
    fun Fragment.replaceChildFragment(
        childFragment: Fragment,
        mainLayoutId: Int,
        tag: String = "",
        inAnim: Int = -1,
        outAnim: Int = -1,
        sharedElement: View? = null,
        sharedElementTransitionName: String? = null
    ) {
        if (isAdded) {
            val backStateName = childFragment.javaClass.name
            val fragManager = childFragmentManager
            try {
                fragManager.popBackStack(backStateName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                fragManager.executePendingTransactions()
            } catch (e: IllegalStateException) {
                Log.d(tag, "Failed to replace child fragment: ${e.message}")
            } catch (e: Exception) {
                Log.d(tag, "Failed to replace child fragment: ${e.message}")
            }
            val fragmentTransaction = fragManager.beginTransaction()
            if (inAnim != -1 && outAnim != -1) {
                fragmentTransaction.setCustomAnimations(inAnim, outAnim, inAnim, outAnim)
            }
            if (sharedElement != null && !sharedElementTransitionName.isNullOrEmpty()) {
                fragmentTransaction.addSharedElement(sharedElement, sharedElementTransitionName)
            }
            fragmentTransaction.replace(mainLayoutId, childFragment)
            if (tag.isEmpty()) {
                fragmentTransaction.addToBackStack(backStateName)
            } else {
                fragmentTransaction.addToBackStack(tag)
            }
            fragmentTransaction.commit()
        }
    }

    fun AppCompatActivity.addFragment(
        fragment: Fragment,
        frameId: Int
    ) {
        supportFragmentManager.inTransaction {
            add(frameId, fragment)
        }
    }

    fun FragmentActivity.addFragment(
        fragment: Fragment,
        frameId: Int,
        addToBackStack: Boolean = true,
        tag: String? = null
    ) {
        supportFragmentManager.inTransaction {
            if (addToBackStack) addToBackStack(tag)
            add(frameId, fragment)
        }
    }

    fun FragmentActivity.replaceFragment(
        fragment: Fragment,
        frameId: Int,
        vararg sharedElements: View,
        addToBackStack: Boolean = true,
        tag: String? = null
    ) {
        supportFragmentManager.inTransaction {
            val ft = replace(frameId, fragment)
            if (addToBackStack) ft.addToBackStack(tag ?: fragment.tag)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                for (view in sharedElements) {
                    ft.addSharedElement(view, view.transitionName)
                }
            }
            ft
        }
    }

    fun AppCompatActivity.replaceFragment(
        fragment: Fragment,
        frameId: Int,
        vararg sharedElements: View,
        addToBackStack: Boolean = true,
        tag: String? = null
    ) {
        supportFragmentManager.inTransaction {
            val ft = replace(frameId, fragment)
            if (addToBackStack) ft.addToBackStack(tag ?: fragment.tag)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                for (view in sharedElements) {
                    ft.addSharedElement(view, view.transitionName)
                }
            }
            ft
        }
    }

    fun AppCompatActivity.popFragment(frameId: Int) {
        supportFragmentManager.inTransaction {
            val fragment = supportFragmentManager.findFragmentById(frameId)
            fragment?.also {
                remove(it)
            }
            this
        }
    }

    private fun FragmentManager.inTransaction(func: androidx.fragment.app.FragmentTransaction.() -> androidx.fragment.app.FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.isVisible(): Boolean {
        return lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    }
}
