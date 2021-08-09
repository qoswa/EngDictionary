package com.qoswantin.engdictionary.wordssearch.usecase

import com.qoswantin.engdictionary.dictionaryservice.model.WordModel
import org.junit.Assert
import org.junit.Test

class WordMapperTest {

    private val mapper = WordMapper()

    @Test
    fun `mapWordModelsToItems wordModels with no meanings results into filtering out these wordModels`() {
        val result = mapper.mapWordModelsToItems(twoDumbWordsAndOneOfThemHasNoMeanings)
        Assert.assertEquals(result.size, 1)
    }

    @Test
    fun `mapWordModelsToItems wordModels with meanings results into the same list length`() {
        val result = mapper.mapWordModelsToItems(dumbWords)
        Assert.assertEquals(result.size, dumbWords.size)
    }

    companion object {
        private val dumbTranslation = WordModel.MeaningLightModel.TranslationModel("trans")
        private val dumbMeaning = WordModel.MeaningLightModel(2, dumbTranslation)
        private val dumbWord = WordModel(0, "word", listOf(dumbMeaning))
        private val dumbWords = listOf(dumbWord)
        private val twoDumbWordsAndOneOfThemHasNoMeanings = listOf(dumbWord, dumbWord.copy(meanings = emptyList()))
    }
}
