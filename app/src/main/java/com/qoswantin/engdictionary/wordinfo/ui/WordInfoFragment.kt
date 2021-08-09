package com.qoswantin.engdictionary.wordinfo.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.qoswantin.engdictionary.R
import com.qoswantin.engdictionary.databinding.FragmentWordInfoBinding
import com.qoswantin.engdictionary.mainactivity.ui.MainActivity
import com.qoswantin.engdictionary.wordinfo.di.DaggerWordInfoComponent
import com.qoswantin.engdictionary.wordinfo.di.WordInfoModule
import com.qoswantin.engdictionary.wordinfo.model.WordInfo
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
        val wordMeaningId = arguments?.getInt(WORD_MEANING_ID_ARG)
            ?: throw IllegalArgumentException("Word meaning id is null")
        DaggerWordInfoComponent.builder()
            .mainActivityComponent(
                (activity as MainActivity).mainActivityComponent
            )
            .wordInfoModule(WordInfoModule(WordInfoFragmentArg(wordMeaningId)))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wordInfoRetryButton.setOnClickListener {
            presenter.onWordInfoRetryClicked()
        }
    }

    override fun showSuccess(wordInfo: WordInfo) {
        with(binding) {
            wordInfoText.isVisible = true
            wordInfoTranslation.isVisible = true
            wordInfoImage.isVisible = true
            wordInfoText.text = wordInfo.text
            wordInfoTranslation.text = wordInfo.translation
            Glide.with(binding.root)
                .load(Uri.parse(wordInfo.imageUrl))
                .placeholder(R.drawable.shape_round_rect)
                .into(binding.wordInfoImage)
            wordInfoRetryButton.isVisible = false
            wordInfoProgressBar.isVisible = false
        }
    }

    override fun showProgress() {
        with(binding) {
            wordInfoText.isVisible = false
            wordInfoTranslation.isVisible = false
            wordInfoImage.isVisible = false
            Glide.with(binding.root).clear(binding.wordInfoImage)
            wordInfoRetryButton.isVisible = false
            wordInfoProgressBar.isVisible = true
        }
    }

    override fun showError() {
        with(binding) {
            wordInfoText.isVisible = false
            wordInfoTranslation.isVisible = false
            wordInfoImage.isVisible = false
            Glide.with(binding.root).clear(binding.wordInfoImage)
            wordInfoRetryButton.isVisible = true
            wordInfoProgressBar.isVisible = false
        }
    }

    companion object {
        private const val WORD_MEANING_ID_ARG = "WORD_MEANING_ID_ARG"
        fun newInstance(wordMeaningId: Int): WordInfoFragment {
            return WordInfoFragment().apply {
                arguments = bundleOf(WORD_MEANING_ID_ARG to wordMeaningId)
            }
        }
    }
}
