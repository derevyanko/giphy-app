package com.example.giphy.presentation.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.giphy.R
import com.example.giphy.databinding.FragmentGiphyPagerBinding
import com.example.giphy.presentation.view.details.adapter.GiphyViewPagerAdapter
import com.example.giphy.presentation.viewmodel.GiphySearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GiphyPagerFragment: Fragment(R.layout.fragment_giphy_pager) {

    private lateinit var binding: FragmentGiphyPagerBinding

    private val viewModel by activityViewModels<GiphySearchViewModel>()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        GiphyViewPagerAdapter()
    }

    private val initialPosition: Int
        get() = arguments?.getInt(KEY_GIPHY_POSITION) ?: 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGiphyPagerBinding.bind(view)

        setupView()

        setCurrentGiphy()
    }

    private fun setupView() {
        setupGiphyViewPager()

        lifecycleScope.launch {
            viewModel.gifs.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setCurrentGiphy() {
        binding.gifsRecyclerView.scrollToPosition(initialPosition)
    }

    private fun setupGiphyViewPager() = with (binding) {
        gifsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        gifsRecyclerView.adapter = adapter
        PagerSnapHelper().attachToRecyclerView(gifsRecyclerView)
    }

    companion object {

        const val KEY_GIPHY_POSITION = "GIPHY_POSITION"
    }
}