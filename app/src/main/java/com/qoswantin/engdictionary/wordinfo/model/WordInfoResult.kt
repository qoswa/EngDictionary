package com.qoswantin.engdictionary.wordinfo.model

sealed class WordInfoResult

data class WordInfoSuccessResult(val wordInfo: WordInfo) : WordInfoResult()
object WordInfoProgressResult : WordInfoResult()
data class WordInfoErrorResult(val throwable: Throwable) : WordInfoResult()
