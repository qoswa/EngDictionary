package com.qoswantin.engdictionary.mainactivity.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.qoswantin.engdictionary.wordinfo.ui.WordInfoFragment
import com.qoswantin.engdictionary.wordssearch.ui.WordsSearchFragment

class FragmentNavigator(
    private val activity: FragmentActivity,
    private val fragmentManager: FragmentManager
) {

    // According to documentation
    // https://developer.android.com/reference/android/content/res/Resources.html#getIdentifier
    // resource can not has id = 0, so we can use it as default "null" value.
    @IdRes
    private var containerIdRes: Int = 0

    fun setContainer(@IdRes container: Int) {
        containerIdRes = container
    }

    fun onBackPressed() {
        if (fragmentManager.backStackEntryCount == 1) {
            activity.finish()
        } else {
            fragmentManager.popBackStack()
        }
    }

    fun openWordsSearchFragment() {
        pushFragment(WordsSearchFragment.newInstance())
    }

    fun openWordInfoFragment(wordMeaningsIds: String) {
        pushFragment(WordInfoFragment.newInstance(wordMeaningsIds))
    }

    private fun pushFragment(fragment: Fragment) {
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
