package com.qoswantin.engdictionary.wordssearch.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.qoswantin.engdictionary.R
import com.qoswantin.engdictionary.databinding.FragmentWordsSearchBinding
import com.qoswantin.engdictionary.mainactivity.ui.MainActivity
import com.qoswantin.engdictionary.wordssearch.adapter.WordsAdapter
import com.qoswantin.engdictionary.wordssearch.di.DaggerWordsSearchComponent
import com.qoswantin.engdictionary.wordssearch.model.WordItem
import com.qoswantin.engdictionary.wordssearch.presenter.WordsSearchPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class WordsSearchFragment : MvpAppCompatFragment(R.layout.fragment_words_search), WordsSearchView {

    @Inject
    lateinit var presenterProvider: Provider<WordsSearchPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }
    private val binding by viewBinding(FragmentWordsSearchBinding::bind)
    private val adapter = WordsAdapter(
        onWordClick = { wordId ->
            presenter.onWordClick(wordId)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerWordsSearchComponent.builder().mainActivityComponent(
            (activity as MainActivity).mainActivityComponent
        )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        binding.wordsSearchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.onQueryTextChanges(newText)
                return true
            }
        })
    }

    private fun initAdapter() {
        binding.wordsSearchResultsList.adapter = adapter
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        val drawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.words_search_recycler_divider,
            null
        )
        drawable?.let {
            divider.setDrawable(it)
        }
        binding.wordsSearchResultsList.addItemDecoration(divider)
    }

    override fun onStart() {
        super.onStart()
        // REASONS:
        // Idle state emitting + Reloading data after process death
        // In case of orientation change emit will be filtered out with distinctUntilChanged
        presenter.onQueryTextChanges(binding.wordsSearchInput.query.toString())
    }

    override fun showWordsSearch(items: List<WordItem>) {
        with(binding) {
            wordsSearchResultsList.isVisible = true
            wordsSearchErrorResultText.isVisible = false
            wordsSearchEmptyResultText.isVisible = false
            wordsSearchProgressBar.hide()
        }
        adapter.submitList(items)
    }

    override fun showEmptyResult() {
        with(binding) {
            wordsSearchResultsList.isVisible = true
            wordsSearchErrorResultText.isVisible = false
            wordsSearchEmptyResultText.isVisible = false
            wordsSearchProgressBar.hide()
        }
    }

    override fun showIdle() {
        with(binding) {
            wordsSearchIdleText.isVisible = true
            wordsSearchResultsList.isVisible = false
            wordsSearchErrorResultText.isVisible = false
            wordsSearchEmptyResultText.isVisible = false
            wordsSearchProgressBar.hide()
        }
    }

    override fun showProgress() {
        with(binding) {
            wordsSearchIdleText.isVisible = false
            wordsSearchResultsList.isVisible = false
            wordsSearchErrorResultText.isVisible = false
            wordsSearchEmptyResultText.isVisible = false
            wordsSearchProgressBar.show()
        }
    }

    override fun showError() {
        with(binding) {
            wordsSearchIdleText.isVisible = false
            wordsSearchResultsList.isVisible = false
            wordsSearchErrorResultText.isVisible = true
            wordsSearchEmptyResultText.isVisible = false
            wordsSearchProgressBar.hide()
        }
    }

    companion object {
        fun newInstance(): WordsSearchFragment {
            return WordsSearchFragment()
        }
    }
}
