package com.soft.crownedjester.programmingquotesapp.domain.use_case.get_favorite_quotes

import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import com.soft.crownedjester.programmingquotesapp.features_quotes.repository.QuoteDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteQuotes (
    private val dbRepository: QuoteDatabaseRepository
) {

    operator fun invoke(): Flow<List<Quote>> {
        return dbRepository.getNotes()
    }
}