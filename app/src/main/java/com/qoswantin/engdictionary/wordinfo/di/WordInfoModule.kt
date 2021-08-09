package com.qoswantin.engdictionary.wordinfo.di

import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import com.qoswantin.engdictionary.wordinfo.presenter.WordInfoPresenter
import com.qoswantin.engdictionary.wordinfo.usecase.WordInfoUseCase
import dagger.Module
import dagger.Provides

@Module
class WordInfoModule {

    @Provides
    @WordInfoScope
    fun provideWordInfoUseCase(dictionaryService: DictionaryService): WordInfoUseCase {
        return WordInfoUseCase(dictionaryService)
    }

    @Provides
    @WordInfoScope
    fun provideWordInfoPresenter(wordInfoUseCase: WordInfoUseCase): WordInfoPresenter {
        return WordInfoPresenter(wordInfoUseCase)
    }
}
