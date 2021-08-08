package com.qoswantin.engdictionary.application

import android.app.Application
import com.qoswantin.engdictionary.application.di.ApplicationComponent
import com.qoswantin.engdictionary.application.di.DaggerApplicationComponent
class EngDictionaryApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }
}
