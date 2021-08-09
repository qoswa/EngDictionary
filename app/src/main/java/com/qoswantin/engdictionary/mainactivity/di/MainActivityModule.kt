package com.qoswantin.engdictionary.mainactivity.di

import com.qoswantin.engdictionary.mainactivity.navigation.FragmentNavigator
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun provideFragmentNavigator(): FragmentNavigator {
        return FragmentNavigator()
    }
}
