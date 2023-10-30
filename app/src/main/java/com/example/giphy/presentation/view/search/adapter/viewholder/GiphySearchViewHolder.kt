package com.example.giphy.presentation.view.search.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.models.search.GiphySearch
import com.example.giphy.R
import com.example.giphy.databinding.ItemGiphySearchBinding
import com.example.giphy.presentation.view.search.adapter.GiphySearchAdapter

class GiphySearchViewHolder(
    private val binding: ItemGiphySearchBinding,
    private val listener: GiphySearchAdapter.GiphyListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GiphySearch?, position: Int) = with(binding) {
        val item = item ?: return@with

        bindUrl(item.url)

        root.setOnClickListener {
            listener.onItemClick(position)
        }

        root.setOnLongClickListener {
            listener.onItemLongClick(item)
            return@setOnLongClickListener true
        }
    }

    private fun bindUrl(url: String) = with (binding) {
        Glide.with(itemView.context)
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .centerCrop()
            .apply(
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(imageView)
    }
}