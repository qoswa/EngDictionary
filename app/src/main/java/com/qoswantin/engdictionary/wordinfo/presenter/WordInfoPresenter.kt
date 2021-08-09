package com.qoswantin.engdictionary.wordinfo.presenter

import com.qoswantin.engdictionary.wordinfo.model.*
import com.qoswantin.engdictionary.wordinfo.ui.WordInfoFragmentArg
import com.qoswantin.engdictionary.wordinfo.ui.WordInfoView
import com.qoswantin.engdictionary.wordinfo.usecase.WordInfoUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class WordInfoPresenter(
    private val wordInfoUseCase: WordInfoUseCase,
    private val arg: WordInfoFragmentArg
) : MvpPresenter<WordInfoView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadWordInfo()
    }

    fun onWordInfoRetryClicked() {
        loadWordInfo()
    }

    private fun loadWordInfo() {
        disposables.clear()
        wordInfoUseCase.loadWordInfo(arg.wordMeaningId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleWordInfoResult(result) },
                Throwable::printStackTrace
            ).let {
                disposables.add(it)
            }
    }

    private fun handleWordInfoResult(result: WordInfoResult) {
        when (result) {
            is WordInfoSuccessResult -> {
                viewState.showSuccess(result.wordInfo)
            }
            is WordInfoProgressResult -> {
                viewState.showProgress()
            }
            is WordInfoErrorResult -> {
                result.throwable.printStackTrace()
                viewState.showError()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
