package com.qoswantin.engdictionary.wordssearch.usecase

import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import com.qoswantin.engdictionary.dictionaryservice.model.TranslationModel
import com.qoswantin.engdictionary.dictionaryservice.model.WordModel
import com.qoswantin.engdictionary.dictionaryservice.model.WordModel.MeaningLightModel
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class WordsSearchUseCaseTest {

    private val dictionaryService: DictionaryService = mock()
    private val mapper: WordsSearchMapper = WordsSearchMapper()

    private val useCase = WordsSearchUseCase(dictionaryService, mapper)

    @Test
    fun `searchWords searching empty string returns IdleState`() {
        useCase.searchWords(EMPTY_STRING)
            .test()
            .assertValue(WordsSearchIdleResult)
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `searchWords searching blank string returns IdleState`() {
        useCase.searchWords(BLANK_STRING)
            .test()
            .assertValue(WordsSearchIdleResult)
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `searchWords service returns words results in Progress and Success state`() {
        whenever(dictionaryService.searchWord(anyString())).thenReturn(Single.just(dumbWords))
        val mappedItems = mapper.mapWordModelsToItems(dumbWords)
        useCase.searchWords(QUERY_STRING)
            .test()
            .assertValues(WordsSearchProgressResult, WordsSearchSuccessResult(mappedItems))
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `searchWords service returns error results in Progress and Error state`() {
        val throwable = Throwable()
        whenever(dictionaryService.searchWord(anyString())).thenReturn(Single.error(throwable))
        useCase.searchWords(QUERY_STRING)
            .test()
            .assertValues(WordsSearchProgressResult, WordsSearchErrorResult(throwable))
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `searchWords service returns empty word list results in Progress and Empty state`() {
        whenever(dictionaryService.searchWord(anyString())).thenReturn(Single.just(emptyList()))
        useCase.searchWords(QUERY_STRING)
            .test()
            .assertValues(WordsSearchProgressResult, WordsSearchEmptyResult)
            .assertNoErrors()
            .assertComplete()
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val BLANK_STRING = "   "
        private const val QUERY_STRING = "some_query"
        private val dumbTranslation = TranslationModel("trans")
        private val dumbMeaning = MeaningLightModel(2, dumbTranslation)
        private val dumbWord = WordModel(0, "word", listOf(dumbMeaning))
        private val dumbWords = listOf(dumbWord, dumbWord)
    }
}
