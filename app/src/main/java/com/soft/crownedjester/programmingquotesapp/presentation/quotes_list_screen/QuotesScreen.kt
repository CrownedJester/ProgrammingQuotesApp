package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components.QuoteItem

@Composable
fun QuotesScreen(
    modifier: Modifier = Modifier,
    quotesViewModel: QuotesViewModel = hiltViewModel()
) {

    val state = quotesViewModel.state.value

    Log.d("QuotesScreen:: ", state.error)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 4.dp)
    ) {
        Text(text = "Quotes:", style = MaterialTheme.typography.h4)

        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp, end = 2.dp, top = 10.dp)
        ) {
            items(state.data!!) { quote ->
                QuoteItem(quote = quote)

            }
        }
    }

}