package com.qoswantin.engdictionary.wordinfo.usecase

import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import com.qoswantin.engdictionary.dictionaryservice.model.MeaningModel
import com.qoswantin.engdictionary.dictionaryservice.model.MeaningModel.*
import com.qoswantin.engdictionary.dictionaryservice.model.TranslationModel
import com.qoswantin.engdictionary.wordinfo.model.WordInfoErrorResult
import com.qoswantin.engdictionary.wordinfo.model.WordInfoProgressResult
import com.qoswantin.engdictionary.wordinfo.model.WordInfoSuccessResult
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class WordInfoUseCaseTest {

    private val dictionaryService: DictionaryService = mock()
    private val mapper: WordInfoMapper = WordInfoMapper()

    private val useCase = WordInfoUseCase(dictionaryService, mapper)

    @Test
    fun `loadWordInfo service returns meanings results in Progress and Success state`() {
        whenever(dictionaryService.wordInfo(anyInt())).thenReturn(Single.just(dumbMeaningModels))
        val mappedItems = mapper.mapMeaningModelsToWordInfo(dumbMeaningModels)
        useCase.loadWordInfo(dumbWordMeaningId)
            .test()
            .assertValues(WordInfoProgressResult, WordInfoSuccessResult(mappedItems))
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `loadWordInfo service returns error results in Progress and Error state`() {
        val throwable = Throwable()
        whenever(dictionaryService.wordInfo(anyInt())).thenReturn(Single.error(throwable))
        useCase.loadWordInfo(dumbWordMeaningId)
            .test()
            .assertValues(WordInfoProgressResult, WordInfoErrorResult(throwable))
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `loadWordInfo service returns empty meanings list results in Progress and Error state`() {
        whenever(dictionaryService.wordInfo(anyInt())).thenReturn(Single.just(emptyList()))
        useCase.loadWordInfo(dumbWordMeaningId)
            .test()
            .assertValueAt(0, WordInfoProgressResult)
            .assertValueAt(1) { it is WordInfoErrorResult }
            .assertNoErrors()
            .assertComplete()
    }

    companion object {
        private const val dumbWordMeaningId = 1
        private val dumbsImages = listOf(ImageModel("imageUrl"))
        private val dumbTranslation = TranslationModel("trans")
        private val dumbMeaningModel = MeaningModel(1, "text", dumbTranslation, dumbsImages)
        private val dumbMeaningModels = listOf(dumbMeaningModel)
    }
}
