package com.soft.crownedjester.programmingquotesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.soft.crownedjester.programmingquotesapp.presentation.favorites_list_screen.FavoritesListScreen
import com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.QuotesScreen
import com.soft.crownedjester.programmingquotesapp.presentation.ui.theme.ProgrammingQuotesAppTheme
import com.soft.crownedjester.programmingquotesapp.presentation.util.Screen
import com.soft.crownedjester.programmingquotesapp.presentation.util.bottomNavItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrammingQuotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val scaffoldState = rememberScaffoldState()
                    val navController = rememberNavController()

                    Scaffold(
                        scaffoldState = scaffoldState,
                        bottomBar = {
                            BottomNavigation(
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = MaterialTheme.colors.primary,
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentRoute = navBackStackEntry?.destination?.route
                                bottomNavItems.onEach { item ->
                                    BottomNavigationItem(
                                        icon = { Icon(item.icon, "") },
                                        label = { Text(text = item.label) },
                                        selected = currentRoute == item.route,
                                        onClick = {
                                            navController.navigate(item.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.QuotesScreen.route,
                            modifier = Modifier.padding(paddingValues = innerPadding)
                        ) {
                            composable(route = Screen.QuotesScreen.route) {
                                QuotesScreen()
                            }
                            composable(route = Screen.FavoritesQuotesScreen.route) {
                                FavoritesListScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}