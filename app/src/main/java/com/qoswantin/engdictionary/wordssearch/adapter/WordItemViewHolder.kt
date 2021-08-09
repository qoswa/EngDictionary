package com.qoswantin.engdictionary.wordssearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qoswantin.engdictionary.databinding.ItemWordBinding
import com.qoswantin.engdictionary.wordssearch.model.WordItem

class WordItemViewHolder(private val binding: ItemWordBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: WordItem, onWordClick: (wordMeaningId: Int) -> Unit) {
        binding.itemWordText.text = item.wordText
        binding.itemWordTranslations.text = item.wordTranslations
        binding.root.setOnClickListener {
            onWordClick(item.firstMeaningId)
        }
    }

    companion object {
        fun newInstance(parent: ViewGroup): WordItemViewHolder {
            return WordItemViewHolder(
                ItemWordBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}
