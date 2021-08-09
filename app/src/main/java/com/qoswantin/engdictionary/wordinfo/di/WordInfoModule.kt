package com.qoswantin.engdictionary.wordinfo.di

import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import com.qoswantin.engdictionary.wordinfo.presenter.WordInfoPresenter
import com.qoswantin.engdictionary.wordinfo.ui.WordInfoFragmentArg
import com.qoswantin.engdictionary.wordinfo.usecase.WordInfoMapper
import com.qoswantin.engdictionary.wordinfo.usecase.WordInfoUseCase
import dagger.Module
import dagger.Provides

@Module
class WordInfoModule(
    private val wordInfoFragmentArg: WordInfoFragmentArg
) {

    @Provides
    @WordInfoScope
    fun provideWordInfoMapper(): WordInfoMapper {
        return WordInfoMapper()
    }

    @Provides
    @WordInfoScope
    fun provideWordInfoUseCase(
        dictionaryService: DictionaryService,
        wordInfoMapper: WordInfoMapper
    ): WordInfoUseCase {
        return WordInfoUseCase(dictionaryService, wordInfoMapper)
    }

    @Provides
    @WordInfoScope
    fun provideWordInfoPresenter(
        wordInfoUseCase: WordInfoUseCase,
    ): WordInfoPresenter {
        return WordInfoPresenter(wordInfoUseCase, wordInfoFragmentArg)
    }
}
