package com.qoswantin.engdictionary.mainactivity.di

import com.qoswantin.engdictionary.application.di.ApplicationComponent
import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import com.qoswantin.engdictionary.mainactivity.navigation.FragmentNavigator
import com.qoswantin.engdictionary.mainactivity.ui.MainActivity
import dagger.Component

@Component(
    dependencies = [
        ApplicationComponent::class
    ],
    modules = [
        MainActivityModule::class
    ]
)
@MainActivityScope
interface MainActivityComponent {
    fun fragmentNavigator(): FragmentNavigator
    fun dictionaryService(): DictionaryService
    fun inject(activity: MainActivity)
}
