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
            HTTPS_SCHEME + meaning.images.first().imageUrl
        )
    }

    companion object {
        const val HTTPS_SCHEME = "https://"
    }
}
