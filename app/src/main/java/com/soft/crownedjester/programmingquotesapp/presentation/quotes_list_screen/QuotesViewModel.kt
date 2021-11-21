package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soft.crownedjester.programmingquotesapp.common.Resource
import com.soft.crownedjester.programmingquotesapp.domain.use_case.get_quotes.GetQuotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotes
) : ViewModel() {

    private val _state = mutableStateOf(QuotesState())
    val state: State<QuotesState> = _state

    private val _revealedQuotesIdsList = MutableStateFlow(listOf<String>())
    val revealedQuotesIdsList: StateFlow<List<String>> = _revealedQuotesIdsList

    init {
        getQuotes()
    }

    private fun getQuotes() {
        getQuotesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = QuotesState(data = result.data)
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

    fun onItemExpanded(cardId: String) {
        if (_revealedQuotesIdsList.value.contains(cardId)) return
        _revealedQuotesIdsList.value = _revealedQuotesIdsList.value.toMutableList().also { list ->
            list.add(cardId)
        }
    }

    fun onItemCollapsed(cardId: String) {
        if (!_revealedQuotesIdsList.value.contains(cardId)) return
        _revealedQuotesIdsList.value = _revealedQuotesIdsList.value.toMutableList().also { list ->
            list.remove(cardId)
        }
    }
}