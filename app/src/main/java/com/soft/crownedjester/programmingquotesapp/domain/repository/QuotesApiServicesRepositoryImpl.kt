package com.soft.crownedjester.programmingquotesapp.domain.repository

import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto

class QuotesApiServicesRepositoryImpl(val api: QuotesApiServicesRepository) : QuotesApiServicesRepository {

    override suspend fun getQuotes(): List<QuotesDto> {
        return api.getQuotes()
    }

    override suspend fun getQuoteById(id: String): QuotesDto {
        return api.getQuoteById(id = id)
    }

    override suspend fun getRandomQuote(): QuotesDto {
        return api.getRandomQuote()
    }

    override suspend fun getQuotesByAuthor(author: String): List<QuotesDto> {
        return api.getQuotesByAuthor(author = author)
    }

}