package com.qoswantin.engdictionary.wordinfo.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.qoswantin.engdictionary.R
import com.qoswantin.engdictionary.databinding.FragmentWordInfoBinding
import com.qoswantin.engdictionary.mainactivity.ui.MainActivity
import com.qoswantin.engdictionary.wordinfo.di.DaggerWordInfoComponent
import com.qoswantin.engdictionary.wordinfo.presenter.WordInfoPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class WordInfoFragment : MvpAppCompatFragment(R.layout.fragment_word_info), WordInfoView {

    @Inject
    lateinit var presenterProvider: Provider<WordInfoPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }
    private val binding by viewBinding(FragmentWordInfoBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerWordInfoComponent.builder().mainActivityComponent(
            (activity as MainActivity).mainActivityComponent
        )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showSuccess() {
        TODO("Not yet implemented")
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val W0RD_MEANINGS_IDS_ARG = "word_meanings_ids"
        fun newInstance(wordMeaningsIds: String): WordInfoFragment {
            return WordInfoFragment().apply {
                arguments = bundleOf(W0RD_MEANINGS_IDS_ARG to wordMeaningsIds)
            }
        }
    }
}
