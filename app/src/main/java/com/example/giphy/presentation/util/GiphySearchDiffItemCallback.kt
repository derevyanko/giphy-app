package com.example.giphy.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.search.GiphySearch

object GiphySearchDiffItemCallback : DiffUtil.ItemCallback<GiphySearch>() {

    override fun areItemsTheSame(oldItem: GiphySearch, newItem: GiphySearch): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GiphySearch, newItem: GiphySearch): Boolean {
        return oldItem.id == newItem.id && oldItem.url == newItem.url
    }
}