package com.qoswantin.engdictionary.wordssearch.di

import com.qoswantin.engdictionary.mainactivity.di.MainActivityComponent
import com.qoswantin.engdictionary.wordssearch.ui.WordsSearchFragment
import dagger.Component

@Component(dependencies = [MainActivityComponent::class], modules = [WordsSearchModule::class])
@WordsSearchScope
interface WordsSearchComponent {
    fun inject(wordsSearchFragment: WordsSearchFragment)
}
