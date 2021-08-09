package com.qoswantin.engdictionary.dictionaryservice

import com.qoswantin.engdictionary.dictionaryservice.model.WordModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryService {
    @GET("/api/public/v1/words/search")
    fun searchWord(@Query("search") searchQuery: String): Single<List<WordModel>>

//    @GET("/api/public/v1/meanings")
//    fun wordInfo(@Query("ids") wordId: Int): Single<>
}
