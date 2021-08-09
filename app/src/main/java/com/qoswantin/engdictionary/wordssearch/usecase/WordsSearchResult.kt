package com.qoswantin.engdictionary.wordssearch.usecase

import com.qoswantin.engdictionary.wordssearch.model.WordItem

sealed class WordsSearchResult

data class WordsSearchSuccessResult(
    val items: List<WordItem>
) : WordsSearchResult()
object WordsSearchProgressResult : WordsSearchResult()
object WordsSearchEmptyResult : WordsSearchResult()
data class WordsSearchErrorResult(val throwable: Throwable) : WordsSearchResult()
object WordsSearchIdleResult : WordsSearchResult()
