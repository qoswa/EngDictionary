package com.qoswantin.engdictionary.mainactivity.di

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.qoswantin.engdictionary.mainactivity.navigation.FragmentNavigator
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(
    private val activity: FragmentActivity
) {

    @Provides
    @MainActivityScope
    fun provideFragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    @MainActivityScope
    fun provideFragmentNavigator(fragmentManager: FragmentManager): FragmentNavigator {
        return FragmentNavigator(activity, fragmentManager)
    }
}
