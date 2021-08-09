package com.qoswantin.engdictionary.dictionaryservice

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DictionaryModule {

    @Provides
    @Singleton
    fun provideDictionaryService(retrofit: Retrofit): DictionaryService {
        return retrofit.create(DictionaryService::class.java)
    }
}
