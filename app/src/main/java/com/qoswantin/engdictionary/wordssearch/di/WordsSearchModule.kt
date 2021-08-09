package com.qoswantin.engdictionary.wordssearch.di

import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import com.qoswantin.engdictionary.mainactivity.navigation.FragmentNavigator
import com.qoswantin.engdictionary.wordssearch.presenter.WordsSearchPresenter
import com.qoswantin.engdictionary.wordssearch.usecase.WordsSearchMapper
import com.qoswantin.engdictionary.wordssearch.usecase.WordsSearchUseCase
import dagger.Module
import dagger.Provides

@Module
class WordsSearchModule {

    @Provides
    @WordsSearchScope
    fun provideWordsSearchMapper(): WordsSearchMapper {
        return WordsSearchMapper()
    }

    @Provides
    @WordsSearchScope
    fun provideWordsSearchUseCase(
        dictionaryService: DictionaryService,
        wordsSearchMapper: WordsSearchMapper
    ): WordsSearchUseCase {
        return WordsSearchUseCase(dictionaryService, wordsSearchMapper)
    }

    @Provides
    @WordsSearchScope
    fun provideWordsSearchPresenter(
        wordsSearchUseCase: WordsSearchUseCase,
        fragmentNavigator: FragmentNavigator
    ): WordsSearchPresenter {
        return WordsSearchPresenter(wordsSearchUseCase, fragmentNavigator)
    }
}
