package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto

@Composable
fun QuoteItemWithActions(
    modifier: Modifier = Modifier,
    quote: QuotesDto,
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
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ActionsRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            actionIconSize = actionIconSize,
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