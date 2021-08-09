package com.qoswantin.engdictionary.wordinfo.presenter

import com.qoswantin.engdictionary.wordinfo.ui.WordInfoView
import com.qoswantin.engdictionary.wordinfo.usecase.WordInfoUseCase
import moxy.MvpPresenter

class WordInfoPresenter(
    private val wordInfoUseCase: WordInfoUseCase
) : MvpPresenter<WordInfoView>()
