package com.soft.crownedjester.programmingquotesapp.domain.repository

import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto

interface IQuotesRepository {

    suspend fun getQuotes(): List<QuotesDto>

    suspend fun getQuoteById(id: String): QuotesDto

    suspend fun getRandomQuote(): QuotesDto

    suspend fun getQuotesByAuthor(author: String): List<QuotesDto>

}