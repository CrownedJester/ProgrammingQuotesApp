package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.app.FrameMetricsAggregator.ANIMATION_DURATION
import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableQuoteCard(
    modifier: Modifier = Modifier,
    quote: QuotesDto,
    isRevealed: Boolean = false,
    cardHeight: Dp,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
) {

    val count = remember { mutableStateOf(0) }
    val offSetX = remember { mutableStateOf(0f) }
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }

    val transition = updateTransition(transitionState, label = "")

    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) (cardOffset - offSetX.value) else (-offSetX.value) }
    )

//    val sendIntent = Intent().apply {
//        action = Intent.ACTION_SEND
//        putExtra(Intent.EXTRA_TEXT, "${quote.quote} \n\bby ${quote.author}")
//        type = "text/plain"
//    }
//
//    val shareIntent = Intent.createChooser(sendIntent, null)
//    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .offset { IntOffset((offSetX.value + offsetTransition).roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    val original = Offset(offSetX.value, 0f)
                    val summed = original + Offset(x = dragAmount, y = 0f)
                    val newValue = Offset(x = summed.x.coerceIn(0f, cardOffset), y = 0f)
                    if (newValue.x >= 10) {
                        onExpand()
                        return@detectHorizontalDragGestures
                    } else if (newValue.x < -0) {
                        onCollapse()
                        return@detectHorizontalDragGestures
                    }
                    change.consumePositionChange()
                    offSetX.value = newValue.x
                }
            }
            .padding(bottom = 8.dp, end = 12.dp, start = 12.dp),
        backgroundColor = Color.Gray,
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(8)
    ) {
        QuoteItem(quote = quote, count = count.value++)
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableSimpleCard(
    modifier: Modifier = Modifier,
    quote: QuotesDto,
    cardHeight: Dp,
    isRevealed: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
) {
    val count = remember { mutableStateOf(0) }
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }

    val cardCollapsedBackgroundColor = Color(0xFF000000)
    val cardExpandedBackgroundColor = Color(0x80000000)
    val transition = updateTransition(targetState = transitionState, "cardTransition")
    val cardBgColor by transition.animateColor(
        label = "cardBgColorTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = {
            if (isRevealed) cardExpandedBackgroundColor else cardCollapsedBackgroundColor
        }
    )

    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset else 0f }
    )

    val cardElevation by transition.animateDp(
        label = "cardElevation",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) 40.dp else 2.dp }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .offset { IntOffset(offsetTransition.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= MIN_DRAG_AMOUNT -> onExpand()
                        dragAmount < -MIN_DRAG_AMOUNT -> onCollapse()
                    }
                }
            },
        backgroundColor = cardBgColor,
        shape = RoundedCornerShape(0.dp),
        elevation = cardElevation,
    ) {
        QuoteItem(quote = quote, count = count.value++)
    }
}