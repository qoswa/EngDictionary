package com.qoswantin.engdictionary.wordssearch.usecase

import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import io.reactivex.rxjava3.core.Observable

class WordsSearchUseCase(
    private val dictionaryService: DictionaryService,
    private val wordMapper: WordMapper
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
                    WordsSearchSuccessResult(wordMapper.mapWordModelsToItems(result))
                }
            }
            .onErrorReturn { WordsSearchErrorResult(it) }
            .startWithItem(WordsSearchProgressResult)
    }
}
