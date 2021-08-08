package com.qoswantin.engdictionary.mainactivity.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qoswantin.engdictionary.R
import com.qoswantin.engdictionary.application.EngDictionaryApplication
import com.qoswantin.engdictionary.mainactivity.di.DaggerMainActivityComponent
import com.qoswantin.engdictionary.mainactivity.di.MainActivityComponent
import com.qoswantin.engdictionary.mainactivity.di.MainActivityModule
import com.qoswantin.engdictionary.mainactivity.navigation.FragmentNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentNavigator: FragmentNavigator

    lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        val applicationComponent = (application as EngDictionaryApplication).applicationComponent
        mainActivityComponent = DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .applicationComponent(applicationComponent)
            .build()
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentNavigator.setContainer(R.id.fragment_container)
        if (savedInstanceState == null) {
            fragmentNavigator.openWordsSearchFragment()
        }
    }

    override fun onBackPressed() {
        fragmentNavigator.onBackPressed()
    }
}
