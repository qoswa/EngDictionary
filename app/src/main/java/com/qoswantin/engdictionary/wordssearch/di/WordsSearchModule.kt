package com.qoswantin.engdictionary.wordssearch.di

import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import com.qoswantin.engdictionary.mainactivity.navigation.FragmentNavigator
import com.qoswantin.engdictionary.wordssearch.presenter.WordsSearchPresenter
import com.qoswantin.engdictionary.wordssearch.usecase.WordMapper
import com.qoswantin.engdictionary.wordssearch.usecase.WordsSearchUseCase
import dagger.Module
import dagger.Provides

@Module
class WordsSearchModule {

    @Provides
    @WordsSearchScope
    fun provideWordMapper(): WordMapper {
        return WordMapper()
    }

    @Provides
    @WordsSearchScope
    fun provideWordsSearchUseCase(
        dictionaryService: DictionaryService,
        wordMapper: WordMapper
    ): WordsSearchUseCase {
        return WordsSearchUseCase(dictionaryService, wordMapper)
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
