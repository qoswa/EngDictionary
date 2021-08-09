package com.qoswantin.engdictionary.wordssearch.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.qoswantin.engdictionary.wordssearch.model.WordItem

class WordsAdapter(
    private val onWordClick: (wordMeaningsIds: String) -> Unit
) : ListAdapter<WordItem, WordItemViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordItemViewHolder {
        return WordItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: WordItemViewHolder, position: Int) {
        holder.bindItem(getItem(position), onWordClick)
    }

    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<WordItem>() {

            override fun areItemsTheSame(oldItem: WordItem, newItem: WordItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WordItem, newItem: WordItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
