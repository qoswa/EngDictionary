package com.qoswantin.engdictionary.wordinfo.di

import com.qoswantin.engdictionary.mainactivity.di.MainActivityComponent
import com.qoswantin.engdictionary.wordinfo.ui.WordInfoFragment
import dagger.Component

@Component(dependencies = [MainActivityComponent::class], modules = [WordInfoModule::class])
@WordInfoScope
interface WordInfoComponent {
    fun inject(wordInfoFragment: WordInfoFragment)
}
