package com.soft.crownedjester.programmingquotesapp.features_quotes.repository

import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteDatabaseRepository {

    fun getNotes(): Flow<List<Quote>>

    suspend fun addQuote(quote: Quote)

    suspend fun deleteQuote(quote: Quote)
}