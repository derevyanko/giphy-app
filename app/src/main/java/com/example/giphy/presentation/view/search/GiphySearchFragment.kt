package com.example.giphy.presentation.view.search

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.base.State
import com.example.domain.models.search.GiphySearch
import com.example.domain.models.search.GiphySearchResponse
import com.example.giphy.R
import com.example.giphy.databinding.FragmentGiphySearchBinding
import com.example.giphy.presentation.util.hideKeyboard
import com.example.giphy.presentation.view.details.GiphyPagerFragment
import com.example.giphy.presentation.view.search.adapter.GiphySearchAdapter
import com.example.giphy.presentation.viewmodel.GiphySearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GiphySearchFragment: Fragment(R.layout.fragment_giphy_search), GiphySearchAdapter.GiphyListener {

    private lateinit var binding: FragmentGiphySearchBinding

    private val viewModel by activityViewModels<GiphySearchViewModel>()

    private val giphyAdapter by lazy(LazyThreadSafetyMode.NONE) {
        GiphySearchAdapter(listener = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGiphySearchBinding.bind(view)

        setupObservers()
        setupView()
    }

    private fun setupObservers() {
        observeGifs()
    }

    private fun observeGifs() {
        lifecycleScope.launch {
            viewModel.gifs.collectLatest {
                giphyAdapter.submitData(it)
            }
        }
    }

    private fun setupView() {
        setupRecyclerView()
        setupSearchView()

        viewModel.query
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach(::updateSearchQuery)
            .launchIn(lifecycleScope)
    }

    private fun setupRecyclerView() = with (binding) {
        gifsRecyclerView.layoutManager = GridLayoutManager(context, 3)
        gifsRecyclerView.adapter = giphyAdapter

        giphyAdapter.addLoadStateListener { state ->
            with(binding) {
                gifsRecyclerView.isVisible = state.refresh != LoadState.Loading
                progress.isVisible = state.refresh == LoadState.Loading
            }
        }
    }

    private fun setupSearchView() = with (binding) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setQuery(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val text = newText ?: ""
                if (text.isEmpty()) {
                    viewModel.setQuery(text)
                    hideKeyboard()
                }
                return false
            }
        })
    }

    private fun updateSearchQuery(searchQuery: String) = with (binding) {
        val query = searchView.query?.toString() ?: ""
        if (query != searchQuery) {
            searchView.setQuery(searchQuery, true)
        }
    }

    private fun showDeleteGiphyConfirmationDialog(item: GiphySearch) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.delete_giphy)
            .setMessage(R.string.are_sure_you_want_to_delete_giphy)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                onPositiveDialogButton(item)
            }
            .setNegativeButton(android.R.string.no, null).show()
    }

    private fun onPositiveDialogButton(item: GiphySearch) {
        viewModel.deleteGiphy(item)

        Toast.makeText(
            requireContext(),
            R.string.giphy_is_removed,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(
            R.id.action_giphySearchFragment_to_giphyPagerFragment,
            bundleOf(GiphyPagerFragment.KEY_GIPHY_POSITION to position)
        )
    }

    override fun onItemLongClick(item: GiphySearch) {
        showDeleteGiphyConfirmationDialog(item)
    }
}