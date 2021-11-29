package com.soft.crownedjester.programmingquotesapp.presentation.favorites_list_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soft.crownedjester.programmingquotesapp.presentation.common_components.QuoteItemWithActions
import com.soft.crownedjester.programmingquotesapp.presentation.util.*

@Composable
fun FavoritesListScreen(
    modifier: Modifier = Modifier,
    favoriteQuotesViewModel: FavoriteQuotesViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val state = favoriteQuotesViewModel.favoritesState
    val revealedCardsIds = favoriteQuotesViewModel.revealedQuotesIdsList.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 4.dp)
    ) {
        Text(text = Screen.FavoritesQuotesScreen.title, style = MaterialTheme.typography.h4)

        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp, end = 2.dp, top = 10.dp)
        ) {
            itemsIndexed(state.value) { count, quote ->
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
                            favoriteQuotesViewModel.onEvent(
                                event = QuotesEvent.AddQuoteToFavorite(
                                    quote
                                ),
                                context = context
                            ) else {
                            favoriteQuotesViewModel.onEvent(
                                event = QuotesEvent.RemoveQuoteFromFavorite(
                                    quote,
                                ),
                                context = context
                            )
                        }
                    },
                    onShare = {
                        favoriteQuotesViewModel.onEvent(
                            QuotesEvent.ShareQuote(quote),
                            context
                        )
                    },
                    onExpand = {
                        favoriteQuotesViewModel.onDraggableEvent(
                            DraggableEvent.OnExpand(
                                quote.id
                            )
                        )
                    },
                    onCollapse = {
                        favoriteQuotesViewModel.onDraggableEvent(
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