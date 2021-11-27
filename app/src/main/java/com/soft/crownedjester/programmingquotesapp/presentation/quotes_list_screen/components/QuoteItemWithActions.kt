package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote

@Composable
fun QuoteItemWithActions(
    modifier: Modifier = Modifier,
    quote: Quote,
    actionIconSize: Dp = 24.dp,
    isRevealed: Boolean,
    cardOffset: Float,
    count: Int,
    onFavoriteBtnClick: () -> Unit,
    onShare: () -> Unit,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        ActionsRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(MaterialTheme.colors.background),
            actionIconSize = actionIconSize,
            isFavorite = quote.isFavorite,
            onShare = { onShare() },
            onFavoriteBtnClick = { onFavoriteBtnClick() }
        )


        DraggableQuoteCard(
            quote = quote,
            isRevealed = isRevealed,
            count = count,
            cardOffset = cardOffset,
            onExpand = { onExpand() },
            onCollapse = { onCollapse() }
        )
    }
}