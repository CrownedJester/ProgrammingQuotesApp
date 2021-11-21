package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen

import android.annotation.SuppressLint
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

const val ACTION_ITEM_SIZE = 56
const val CARD_HEIGHT = 56
const val CARD_OFFSET = 168f

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun QuotesScreen(
    modifier: Modifier = Modifier,
    quotesViewModel: QuotesViewModel = hiltViewModel()
) {
    var count = 0
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
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//                    ActionsRow(
//                        actionIconSize = ACTION_ITEM_SIZE.dp,
//                        onRemoveFavorite = { /*TODO*/ },
//                        onShare = { /*TODO*/ },
//                        onFavorite = {/*TODO*/ }
//                    )


                QuoteItem(quote = quote, count = count++)
//                    DraggableSimpleCard(
//                        quote = quote,
//                        isRevealed = quotesViewModel.revealedQuotesIdsList.value.contains(quote.id),
//                        cardHeight = CARD_HEIGHT.dp,
//                        cardOffset = CARD_OFFSET,
//                        onExpand = { quotesViewModel.onItemExpanded(quote.id) },
//                        onCollapse = { quotesViewModel.onItemCollapsed(quote.id) }
//                    )
//                }
            }
        }
    }
}