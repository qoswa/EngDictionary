package com.qoswantin.engdictionary.mainactivity.navigation

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.qoswantin.engdictionary.wordinfo.ui.WordInfoFragment
import com.qoswantin.engdictionary.wordssearch.ui.WordsSearchFragment

class FragmentNavigator {

    // According to documentation
    // https://developer.android.com/reference/android/content/res/Resources.html#getIdentifier
    // resource can not has id = 0, so we can use it as default "null" value.
    @IdRes
    private var containerIdRes: Int = 0

    fun setContainer(@IdRes container: Int) {
        containerIdRes = container
    }

    fun onBackPressed(supportFragmentManager: FragmentManager, activity: Activity) {
        if (supportFragmentManager.backStackEntryCount == 1) {
            activity.finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    fun openWordsSearchFragment(supportFragmentManager: FragmentManager) {
        pushFragment(WordsSearchFragment.newInstance(), supportFragmentManager)
    }

    fun openWordInfoFragment(wordMeaningId: Int, fragmentManager: FragmentManager) {
        pushFragment(WordInfoFragment.newInstance(wordMeaningId), fragmentManager)
    }

    private fun pushFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        checkForContainer()
        fragmentManager
            .beginTransaction()
            .replace(containerIdRes, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun checkForContainer() {
        check(containerIdRes != 0) {
            "You must initFragmentNavigator with containerId first."
        }
    }
}
