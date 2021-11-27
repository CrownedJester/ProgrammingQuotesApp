package com.soft.crownedjester.programmingquotesapp.domain.use_case.add_to_favorite

import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import com.soft.crownedjester.programmingquotesapp.features_quotes.repository.QuoteDatabaseRepository

class AddQuoteToFavorite(
    private val dbRepository: QuoteDatabaseRepository
) {

    suspend operator fun invoke(quote: Quote) {
        quote.isFavorite = true
        dbRepository.addQuote(quote)
    }
}