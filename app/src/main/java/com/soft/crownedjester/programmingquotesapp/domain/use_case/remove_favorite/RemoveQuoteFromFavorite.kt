package com.soft.crownedjester.programmingquotesapp.domain.use_case.remove_favorite

import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import com.soft.crownedjester.programmingquotesapp.features_quotes.repository.QuoteDatabaseRepository
import javax.inject.Inject

class RemoveQuoteFromFavorite (
    private val dbRepository: QuoteDatabaseRepository
) {

    suspend operator fun invoke(quote: Quote) {
        dbRepository.deleteQuote(quote)
    }
}