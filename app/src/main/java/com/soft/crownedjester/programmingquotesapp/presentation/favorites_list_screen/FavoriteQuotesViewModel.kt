package com.soft.crownedjester.programmingquotesapp.presentation.favorites_list_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import com.soft.crownedjester.programmingquotesapp.domain.use_case.QuoteUseCases
import com.soft.crownedjester.programmingquotesapp.presentation.util.DraggableEvent
import com.soft.crownedjester.programmingquotesapp.presentation.util.QuotesEvent
import com.soft.crownedjester.programmingquotesapp.presentation.util.onItemCollapsed
import com.soft.crownedjester.programmingquotesapp.presentation.util.onItemExpanded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteQuotesViewModel @Inject constructor(
    private val useCases: QuoteUseCases
) : ViewModel() {

    private val _favoritesState = mutableStateOf(listOf<Quote>())
    val favoritesState: State<List<Quote>> = _favoritesState

    private var getFavoritesJob: Job? = null

    private val _revealedQuotesIdsList = MutableStateFlow(listOf<String>())
    val revealedQuotesIdsList: StateFlow<List<String>> = _revealedQuotesIdsList

    init {
        getFavorites()
    }

    private fun getFavorites() {
        getFavoritesJob?.cancel()
        getFavoritesJob = useCases.getFavoriteQuotes()
            .onEach { quotes ->
                _favoritesState.value = quotes
            }.launchIn(viewModelScope)
    }

    fun onEvent(event: QuotesEvent, context: Context) {
        when (event) {
            is QuotesEvent.AddQuoteToFavorite -> {
                viewModelScope.launch {
                    useCases.addQuoteToFavorite(event.quote)
                }
                Log.i("QuotesViewModel:: ", "Successfully cached")
            }

            is QuotesEvent.RemoveQuoteFromFavorite -> {
                viewModelScope.launch {
                    useCases.removeQuoteFromFavorite(event.quote)
                }
                Log.i("QuotesViewModel:: ", "Successfully unCached")
            }

            is QuotesEvent.ShareQuote -> {
                viewModelScope.launch {
                    useCases.shareQuote(event.quote, context = context)
                }
                Log.i("QuotesViewModel:: ", "Successfully shared")
            }
        }
    }

    fun onDraggableEvent(event: DraggableEvent) {
        when (event) {
            is DraggableEvent.OnExpand -> {
                onItemExpanded(event.id, _revealedQuotesIdsList)
            }
            is DraggableEvent.OnCollapse ->
                onItemCollapsed(event.id, _revealedQuotesIdsList)
        }
    }

}