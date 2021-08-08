package com.qoswantin.engdictionary.wordssearch.usecase

import com.qoswantin.engdictionary.application.di.DictionaryService
import com.qoswantin.engdictionary.wordssearch.model.*
import io.reactivex.rxjava3.core.Observable

class WordsSearchUseCase(
    private val dictionaryService: DictionaryService
) {
    fun searchWords(searchQuery: String): Observable<WordsSearchResult> {
        if (searchQuery.isBlank()) {
            return Observable.just(WordsSearchIdleResult)
        }
        return dictionaryService.searchWord(searchQuery)
            .toObservable()
            .map { result ->
                if (result.isEmpty()) {
                    WordsSearchEmptyResult
                } else {
                    WordsSearchSuccessResult(mapWordModelsToItems(result))
                }
            }
            .onErrorReturn { WordsSearchErrorResult(it) }
            .startWithItem(WordsSearchProgressResult)
    }

    private fun mapWordModelsToItems(wordModels: List<WordModel>): List<WordItem> {
        return wordModels.map { wordModel ->
            WordItem(
                wordModel.id,
                wordModel.text,
                wordModel.meanings.joinToString { it.translation.translationText }
            )
        }
    }
}
