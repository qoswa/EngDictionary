package com.qoswantin.engdictionary.application.di

import com.qoswantin.engdictionary.dictionaryservice.DictionaryModule
import com.qoswantin.engdictionary.dictionaryservice.DictionaryService
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        DictionaryModule::class
    ]
)
@Singleton
interface ApplicationComponent {
    fun dictionaryService(): DictionaryService
}
