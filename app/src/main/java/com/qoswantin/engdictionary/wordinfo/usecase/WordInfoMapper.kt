package com.qoswantin.engdictionary.wordinfo.usecase

import com.qoswantin.engdictionary.dictionaryservice.model.MeaningModel
import com.qoswantin.engdictionary.wordinfo.model.WordInfo

class WordInfoMapper {

    fun mapMeaningModelsToWordInfo(meanings: List<MeaningModel>): WordInfo {
        val meaning = meanings.first()
        return WordInfo(
            meaning.id,
            meaning.text,
            meaning.translation.translationText,
            meaning.images.first().imageUrl
        )
    }
}
