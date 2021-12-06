package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soft.crownedjester.programmingquotesapp.common.Resource
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
class QuotesViewModel @Inject constructor(
    private val useCases: QuoteUseCases
) : ViewModel() {

    private val _favoritesState = mutableStateOf(listOf<Quote>())
    private var getFavoritesJob: Job? = null

    private val _state = mutableStateOf(QuotesState())
    val state: State<QuotesState> = _state

    private val _revealedQuotesIdsList = MutableStateFlow(listOf<String>())
    val revealedQuotesIdsList: StateFlow<List<String>> = _revealedQuotesIdsList


    init {
        getQuotes()
    }

    private fun getQuotes() {
        getFavorites()
        useCases.getQuotes().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = QuotesState(data = result.data)

                    applyFavoritesToRemotes(_favoritesState.value, _state.value.data)

                    Log.d(
                        this@QuotesViewModel.javaClass.toString() + ":: ",
                        "Data successfully loaded"
                    )
                }

                is Resource.Loading -> {
                    _state.value = QuotesState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = QuotesState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getFavorites() {
        getFavoritesJob = useCases.getFavoriteQuotes()
            .onEach { quotes ->
                _favoritesState.value = quotes
                Log.d("GetFavorites@QViewModel", _favoritesState.value.toString())
                applyFavoritesToRemotes(_favoritesState.value, _state.value.data)
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

    private fun applyFavoritesToRemotes(cached: List<Quote>, remotes: List<Quote>?) {

        remotes!!.let { }
        //clear all favorites status if exists
        if (cached.isNotEmpty()) {
            remotes.map { quote ->
                quote.isFavorite = false
            }
        }

        // using to count used cached to stop loop when all cached is already found
        var countCachedUsedOverall = 0
        for (remoteQuote in remotes) {

            //stop when all cached found
            if (countCachedUsedOverall == cached.size) {
                break
            }

            for (cachedQuote in cached) {
                if (remoteQuote.id == cachedQuote.id) {
                    remoteQuote.isFavorite = true
                    Log.i(
                        "ApplyFavtoRe@QViewModel",
                        "Quote with id  id=${remoteQuote.id} found in cached"
                    )
                    countCachedUsedOverall++
                    break
                }
            }
        }
    }
}