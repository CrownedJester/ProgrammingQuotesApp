package com.soft.crownedjester.programmingquotesapp.presentation.util

import com.soft.crownedjester.programmingquotesapp.domain.model.Quote

sealed class QuotesEvent {
    data class AddQuoteToFavorite(val quote: Quote) : QuotesEvent()
    data class RemoveQuoteFromFavorite(val quote: Quote) : QuotesEvent()
    data class ShareQuote(val quote: Quote): QuotesEvent()
}
