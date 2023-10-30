package com.example.giphy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import com.example.domain.models.search.GiphySearch
import com.example.domain.usecases.DeleteGiphyUseCase
import com.example.domain.usecases.GetGiphyBySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class GiphySearchViewModel @Inject constructor(
    coroutineContext: CoroutineContext,
    private val getGiphyBySearchUseCase: GetGiphyBySearchUseCase,
    private val deleteGiphyUseCase: DeleteGiphyUseCase
): ViewModel() {

    private val scope = CoroutineScope(coroutineContext)

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val gifs = query
        .flatMapLatest {
            getGiphyBySearchUseCase(it)
        }
        .cachedIn(scope)

    fun setQuery(query: String) {
        _query.tryEmit(query)
    }

    fun deleteGiphy(giphy: GiphySearch) = scope.launch {
        deleteGiphyUseCase.invoke(giphy)
    }
}