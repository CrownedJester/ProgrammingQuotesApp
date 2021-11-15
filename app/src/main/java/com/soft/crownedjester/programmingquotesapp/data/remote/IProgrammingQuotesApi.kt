package com.soft.crownedjester.programmingquotesapp.data.remote

import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface IProgrammingQuotesApi {

    @GET("quotes")
    suspend fun getQuotes(): List<QuotesDto>

    @GET("quotes/author/{author}")
    suspend fun getQuotesByAuthor(@Path("author") author: String): List<QuotesDto>

    @GET("quotes/{id}")
    suspend fun getQuoteById(@Path("id") id: String): QuotesDto

    @GET("quotes/random")
    suspend fun getRandomQuote(): QuotesDto
}