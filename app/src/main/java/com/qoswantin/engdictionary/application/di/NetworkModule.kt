package com.qoswantin.engdictionary.application.di

import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Whenever Dagger needs to provide an instance of type LoginRetrofitService,
        // this code (the one inside the @Provides method) is run.
        return Retrofit.Builder()
            .baseUrl(DICTIONARY_BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryService(retrofit: Retrofit): DictionaryService {
        return retrofit.create(DictionaryService::class.java)
    }

    companion object {
        private const val DICTIONARY_BASE_URL = "https://dictionary.skyeng.ru"
    }
}
