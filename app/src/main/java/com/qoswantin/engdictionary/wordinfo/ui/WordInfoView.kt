package com.qoswantin.engdictionary.wordinfo.ui

import com.qoswantin.engdictionary.wordinfo.model.WordInfo
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface WordInfoView : MvpView {
    fun showSuccess(wordInfo: WordInfo)
    fun showProgress()
    fun showError()
}
