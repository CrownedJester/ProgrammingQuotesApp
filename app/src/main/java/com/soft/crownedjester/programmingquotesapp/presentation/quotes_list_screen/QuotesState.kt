package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen

import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto

class QuotesState(
    val isLoading: Boolean = false,
    val data: List<QuotesDto>? = emptyList(),
    val error: String = ""
) {

}
