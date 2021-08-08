package com.qoswantin.engdictionary.application.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class
    ]
)
@Singleton
interface ApplicationComponent {
    fun dictionaryService(): DictionaryService
}