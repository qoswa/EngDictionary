package com.qoswantin.engdictionary.wordssearch.ui

import com.qoswantin.engdictionary.wordssearch.model.WordItem
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface WordsSearchView : MvpView {
    fun showEmptyResult()
    fun showWordsSearch(items: List<WordItem>)
    fun showProgress()
    fun showError()
    fun showIdle()
}
