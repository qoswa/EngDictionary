package com.qoswantin.engdictionary.wordinfo.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface WordInfoView : MvpView {
    fun showSuccess()
    fun showProgress()
    fun showError()
}
