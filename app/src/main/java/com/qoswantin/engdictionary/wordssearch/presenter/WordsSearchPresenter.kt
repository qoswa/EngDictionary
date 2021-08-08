package com.qoswantin.engdictionary.wordssearch.presenter

import com.qoswantin.engdictionary.wordssearch.ui.WordsSearchView
import com.qoswantin.engdictionary.wordssearch.usecase.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class WordsSearchPresenter(
    private val wordsSearchUseCase: WordsSearchUseCase
) : MvpPresenter<WordsSearchView>() {

    private val querySubject: PublishSubject<String> = PublishSubject.create()
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        querySubject
            .distinctUntilChanged()
            .observeOn(Schedulers.io())
            .debounce(DEBOUNCE_TIME_MILLIS, TimeUnit.MILLISECONDS)
            .switchMap { query ->
                wordsSearchUseCase.searchWords(query)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleWordsSearchResult(result) },
                Throwable::printStackTrace
            ).let {
                disposables.add(it)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    fun onQueryTextChanges(queryString: String) {
        querySubject.onNext(queryString)
    }

    private fun handleWordsSearchResult(wordsSearchResult: WordsSearchResult) {
        when (wordsSearchResult) {
            is WordsSearchSuccessResult -> viewState?.showWordsSearch(wordsSearchResult.items)
            is WordsSearchProgressResult -> viewState?.showProgress()
            is WordsSearchErrorResult -> viewState?.showError()
            is WordsSearchEmptyResult -> viewState?.showEmptyResult()
            is WordsSearchIdleResult -> viewState?.showIdle()
        }
    }

    companion object {
        private const val DEBOUNCE_TIME_MILLIS = 500L
    }
}
