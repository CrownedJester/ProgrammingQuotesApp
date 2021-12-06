package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soft.crownedjester.programmingquotesapp.presentation.common_components.QuoteItemWithActions
import com.soft.crownedjester.programmingquotesapp.presentation.util.*


@ExperimentalAnimationApi
@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun QuotesScreen(
    quotesViewModel: QuotesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = quotesViewModel.state.value
    val revealedCardsIds = quotesViewModel.revealedQuotesIdsList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 4.dp),
    ) {
        Text(text = Screen.QuotesScreen.title, style = MaterialTheme.typography.h4)

        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)

        if (state.error.isNotBlank()) {
            Log.e("QuotesScreen", state.error)

            ErrorStateScreen(modifier = Modifier.fillMaxSize(), message = state.error)

        } else if (state.isLoading) {
            Log.i("QuotesScreen", "Data is Loading")

            LoadingDataStateScreen(
                modifier = Modifier.fillMaxSize(),
                message = "Preparing data..."
            )

        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 4.dp, end = 2.dp, top = 10.dp)
            ) {
                itemsIndexed(state.data!!) { count, quote ->
                    QuoteItemWithActions(
                        modifier = Modifier
                            .fillMaxWidth(),
                        quote = quote,
                        actionIconSize = ACTION_ITEM_SIZE.dp,
                        isRevealed = revealedCardsIds.value.contains(quote.id),
                        cardOffset = CARD_OFFSET.dp(),
                        count = count,
                        onFavoriteBtnClick = {
                            if (!quote.isFavorite)
                                quotesViewModel.onEvent(
                                    event = QuotesEvent.AddQuoteToFavorite(
                                        quote
                                    ),
                                    context = context
                                ) else {
                                quotesViewModel.onEvent(
                                    event = QuotesEvent.RemoveQuoteFromFavorite(
                                        quote,
                                    ),
                                    context = context
                                )
                            }
                        },
                        onShare = {
                            quotesViewModel.onEvent(
                                QuotesEvent.ShareQuote(quote),
                                context
                            )
                        },
                        onExpand = { quotesViewModel.onDraggableEvent(DraggableEvent.OnExpand(quote.id)) },
                        onCollapse = {
                            quotesViewModel.onDraggableEvent(
                                DraggableEvent.OnCollapse(
                                    quote.id
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingDataStateScreen(modifier: Modifier = Modifier, message: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Text(text = message)

    }
}

@Composable
private fun ErrorStateScreen(modifier: Modifier = Modifier, message: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.h6
        )
    }

}