package com.qoswantin.engdictionary.wordssearch.usecase

import com.qoswantin.engdictionary.dictionaryservice.model.WordModel
import com.qoswantin.engdictionary.wordssearch.model.WordItem

class WordMapper {

    fun mapWordModelsToItems(wordModels: List<WordModel>): List<WordItem> {
        return wordModels
            .filter { wordModel ->
                wordModel.meanings.isNotEmpty()
            }
            .map { wordModel ->
                WordItem(
                    wordModel.id,
                    wordModel.text,
                    wordModel.meanings.joinToString { it.translation.translationText },
                    wordModel.meanings.joinToString(separator = MEANINGS_IDS_SEPARATOR) { it.id.toString() }
                )
            }
    }

    companion object {
        private const val MEANINGS_IDS_SEPARATOR = ","
    }
}
