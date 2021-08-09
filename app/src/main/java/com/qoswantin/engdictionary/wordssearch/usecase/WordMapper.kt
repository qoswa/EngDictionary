package com.qoswantin.engdictionary.wordssearch.usecase

import com.qoswantin.engdictionary.wordssearch.model.WordItem
import com.qoswantin.engdictionary.wordssearch.model.WordModel

class WordMapper {

    fun mapWordModelsToItems(wordModels: List<WordModel>): List<WordItem> {
        return wordModels.map { wordModel ->
            WordItem(
                wordModel.id,
                wordModel.text,
                wordModel.meanings.joinToString { it.translation.translationText }
            )
        }
    }
}
