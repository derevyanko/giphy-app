package com.example.giphy.presentation.view.details.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.models.search.GiphySearch
import com.example.giphy.R
import com.example.giphy.databinding.ItemGiphyDetailBinding

class GiphyDetailViewHolder(
    private val binding: ItemGiphyDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GiphySearch?) {
        val item = item ?: return

        bindUrl(item.url)
    }

    private fun bindUrl(url: String) = with (binding) {
        Glide.with(itemView.context)
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .apply(
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(imageView)
    }
}