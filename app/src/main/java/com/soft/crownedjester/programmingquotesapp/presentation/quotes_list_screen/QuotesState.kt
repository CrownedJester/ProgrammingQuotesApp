package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen

import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote

class QuotesState(
    val isLoading: Boolean = false,
    val data: List<Quote>? = emptyList(),
    val error: String = ""
) {

}
