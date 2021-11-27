package com.soft.crownedjester.programmingquotesapp.domain.use_case

import com.soft.crownedjester.programmingquotesapp.domain.use_case.add_to_favorite.AddQuoteToFavorite
import com.soft.crownedjester.programmingquotesapp.domain.use_case.get_favorite_quotes.GetFavoriteQuotes
import com.soft.crownedjester.programmingquotesapp.domain.use_case.get_quotes.GetQuotes
import com.soft.crownedjester.programmingquotesapp.domain.use_case.remove_favorite.RemoveQuoteFromFavorite
import com.soft.crownedjester.programmingquotesapp.domain.use_case.share_quote.ShareQuote

class QuoteUseCases(
    val getQuotes: GetQuotes,
    val getFavoriteQuotes: GetFavoriteQuotes,
    val addQuoteToFavorite: AddQuoteToFavorite,
    val removeQuoteFromFavorite: RemoveQuoteFromFavorite,
    val shareQuote: ShareQuote,
)
