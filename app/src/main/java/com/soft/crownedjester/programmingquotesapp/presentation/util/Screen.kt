package com.soft.crownedjester.programmingquotesapp.presentation.util

sealed class Screen(val route: String, val title: String) {
    object QuotesScreen : Screen("quotes_screen", "Quotes: ")
    object FavoritesQuotesScreen : Screen("favorites_screen", "Favorites Quotes: ")
}