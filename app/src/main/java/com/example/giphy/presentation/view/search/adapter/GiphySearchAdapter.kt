package com.example.giphy.presentation.view.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.search.GiphySearch
import com.example.giphy.R
import com.example.giphy.databinding.ItemGiphySearchBinding
import com.example.giphy.presentation.util.GiphySearchDiffItemCallback
import com.example.giphy.presentation.view.search.adapter.viewholder.GiphySearchViewHolder

class GiphySearchAdapter(
    private val listener: GiphyListener
) : PagingDataAdapter<GiphySearch, GiphySearchViewHolder>(GiphySearchDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphySearchViewHolder {
        val binding = ItemGiphySearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphySearchViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: GiphySearchViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    interface GiphyListener {

        fun onItemClick(position: Int)
        fun onItemLongClick(item: GiphySearch)
    }
}