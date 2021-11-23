package com.soft.crownedjester.programmingquotesapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote

data class QuotesDto(
    val author: String,
    @SerializedName("en")
    val quote: String,
    val id: String
)

fun QuotesDto.toQuote(): Quote {
    return Quote(
        id = id,
        author = author,
        quote = quote
    )
}