package com.qoswantin.engdictionary.wordinfo.usecase

import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import com.qoswantin.engdictionary.wordinfo.model.WordInfoErrorResult
import com.qoswantin.engdictionary.wordinfo.model.WordInfoProgressResult
import com.qoswantin.engdictionary.wordinfo.model.WordInfoResult
import com.qoswantin.engdictionary.wordinfo.model.WordInfoSuccessResult
import io.reactivex.rxjava3.core.Observable

class WordInfoUseCase(
    private val dictionaryService: DictionaryService,
    private val wordInfoMapper: WordInfoMapper
) {

    fun loadWordInfo(wordMeaningId: Int): Observable<WordInfoResult> {
        return dictionaryService.wordInfo(wordMeaningId)
            .toObservable()
            .map { meaningsModels ->
                if (meaningsModels.isEmpty()) {
                    WordInfoErrorResult
                } else {
                    WordInfoSuccessResult(wordInfoMapper.mapMeaningModelsToWordInfo(meaningsModels))
                }
            }
            .onErrorReturnItem(WordInfoErrorResult)
            .startWithItem(WordInfoProgressResult)
    }
}
