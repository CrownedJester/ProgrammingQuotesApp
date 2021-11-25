package com.soft.crownedjester.programmingquotesapp.features_quotes.repository

import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import com.soft.crownedjester.programmingquotesapp.features_quotes.data.data_source.QuoteDao
import kotlinx.coroutines.flow.Flow

class QuoteDatabaseRepositoryImpl(private val quoteDao: QuoteDao) : QuoteDatabaseRepository {
    override fun getNotes(): Flow<List<Quote>> =
        quoteDao.getFavoriteQuotes()


    override suspend fun addQuote(quote: Quote) =
        quoteDao.addQuote(quote)


    override suspend fun deleteQuote(quote: Quote) =
        quoteDao.deleteQuote(quote)

}