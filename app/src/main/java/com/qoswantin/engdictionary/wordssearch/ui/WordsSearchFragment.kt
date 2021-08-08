package com.qoswantin.engdictionary.wordssearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.qoswantin.engdictionary.R
import com.qoswantin.engdictionary.databinding.FragmentWordsSearchBinding
import com.qoswantin.engdictionary.mainactivity.ui.MainActivity
import com.qoswantin.engdictionary.wordssearch.presenter.WordsSearchPresenter
import com.qoswantin.engdictionary.wordssearch.adapter.WordsAdapter
import com.qoswantin.engdictionary.wordssearch.di.DaggerWordsSearchComponent
import com.qoswantin.engdictionary.wordssearch.model.WordItem
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class WordsSearchFragment : MvpAppCompatFragment(), WordsSearchView {

    @Inject
    lateinit var presenterProvider: Provider<WordsSearchPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }
    private var binding: FragmentWordsSearchBinding? = null

    lateinit var adapter: WordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerWordsSearchComponent.builder().mainActivityComponent(
            (activity as MainActivity).mainActivityComponent
        )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordsSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        binding?.wordsSearchInput?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        adapter = WordsAdapter()
        val recycler = binding?.wordsSearchResultsList
        recycler?.adapter = adapter
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        val drawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.words_search_recycler_divider,
            null
        )
        drawable?.let {
            divider.setDrawable(it)
        }
        recycler?.addItemDecoration(divider)
    }

    override fun onStart() {
        super.onStart()
        binding?.wordsSearchInput?.query?.toString()?.let {
            // REASONS:
            // Idle state emitting + Reloading data after process death
            // In case of orientation change emit will be filtered out with distinctUntilChanged
            presenter.onQueryTextChanges(it)
        }
    }

    override fun showWordsSearch(items: List<WordItem>) {
        binding?.let {
            it.wordsSearchResultsList.isVisible = true
            it.wordsSearchErrorResultText.isVisible = false
            it.wordsSearchEmptyResultText.isVisible = false
            it.wordsSearchProgressBar.hide()
        }
        adapter.submitList(items)
    }

    override fun showEmptyResult() {
        binding?.let {
            it.wordsSearchIdleText.isVisible = false
            it.wordsSearchResultsList.isVisible = false
            it.wordsSearchErrorResultText.isVisible = false
            it.wordsSearchEmptyResultText.isVisible = true
            it.wordsSearchProgressBar.hide()
        }
    }

    override fun showIdle() {
        binding?.let {
            it.wordsSearchIdleText.isVisible = true
            it.wordsSearchResultsList.isVisible = false
            it.wordsSearchErrorResultText.isVisible = false
            it.wordsSearchEmptyResultText.isVisible = false
            it.wordsSearchProgressBar.hide()
        }
    }

    override fun showProgress() {
        binding?.let {
            it.wordsSearchIdleText.isVisible = false
            it.wordsSearchResultsList.isVisible = false
            it.wordsSearchErrorResultText.isVisible = false
            it.wordsSearchEmptyResultText.isVisible = false
            it.wordsSearchProgressBar.show()
        }
    }

    override fun showError() {
        binding?.let {
            it.wordsSearchIdleText.isVisible = false
            it.wordsSearchResultsList.isVisible = false
            it.wordsSearchErrorResultText.isVisible = true
            it.wordsSearchEmptyResultText.isVisible = false
            it.wordsSearchProgressBar.hide()
        }
    }

    companion object {
        fun newInstance(): WordsSearchFragment {
            return WordsSearchFragment()
        }
    }
}
