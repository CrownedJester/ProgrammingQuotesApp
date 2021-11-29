package com.soft.crownedjester.programmingquotesapp.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Dashboard",
        icon = Icons.Filled.Dashboard,
        route = Screen.QuotesScreen.route
    ),
    BottomNavItem(
        label = "Favorites",
        icon = Icons.Filled.Favorite,
        route = Screen.FavoritesQuotesScreen.route
    )
)
