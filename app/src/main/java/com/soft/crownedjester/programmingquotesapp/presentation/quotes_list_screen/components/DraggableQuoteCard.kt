package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import com.soft.crownedjester.programmingquotesapp.presentation.ui.theme.Vanilla
import kotlin.math.roundToInt

const val DURATION_ANIMATION = 500
const val MIN_DRAG_AMOUNT = 6

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableQuoteCard(
    modifier: Modifier = Modifier,
    quote: Quote,
    count: Int,
    isRevealed: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
) {

//    val sendIntent = Intent().apply {
//        action = Intent.ACTION_SEND
//        putExtra(Intent.EXTRA_TEXT, "${quote.quote} \n\bby ${quote.author}")
//        type = "text/plain"
//    }
//
//    val shareIntent = Intent.createChooser(sendIntent, null)
//    val context = LocalContext.current

    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }

    val transition = updateTransition(targetState = transitionState, "cardTransition")


    val cardBgColor by transition.animateColor(
        label = "cardBgColorTransition",
        transitionSpec = { tween(durationMillis = DURATION_ANIMATION) },
        targetValueByState = {
            if (isRevealed) Vanilla else Color(quote.color)//first value if expanded, second if collapsed
        }
    )

    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = DURATION_ANIMATION) },
        targetValueByState = { if (isRevealed) cardOffset else 0f }
    )

    val cardElevation by transition.animateDp(
        label = "cardElevation",
        transitionSpec = { tween(durationMillis = DURATION_ANIMATION) },
        targetValueByState = { if (isRevealed) 40.dp else 2.dp }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(offsetTransition.roundToInt(), 0) }
            .padding(bottom = 8.dp, end = 12.dp, start = 12.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= MIN_DRAG_AMOUNT -> onExpand()
                        dragAmount < -MIN_DRAG_AMOUNT -> onCollapse()
                    }
                }
            },
        backgroundColor = cardBgColor,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = cardElevation,
        content = {
            QuoteItem(quote = quote, count = count)
        }
    )
}