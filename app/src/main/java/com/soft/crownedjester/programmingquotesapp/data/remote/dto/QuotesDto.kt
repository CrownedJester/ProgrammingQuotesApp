package com.soft.crownedjester.programmingquotesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuotesDto(
    val author: String,
    @SerializedName("en")
    val quote: String,
    val id: String
)