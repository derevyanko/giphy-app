package com.example.giphy.presentation.view.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.domain.models.search.GiphySearch
import com.example.giphy.R
import com.example.giphy.databinding.ItemGiphyDetailBinding
import com.example.giphy.presentation.util.GiphySearchDiffItemCallback
import com.example.giphy.presentation.view.details.adapter.viewholder.GiphyDetailViewHolder

class GiphyViewPagerAdapter : PagingDataAdapter<GiphySearch, GiphyDetailViewHolder>(GiphySearchDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyDetailViewHolder {
        val binding = ItemGiphyDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphyDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GiphyDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
