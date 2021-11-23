package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.soft.crownedjester.programmingquotesapp.R

@Composable
fun ActionsRow(
    modifier: Modifier = Modifier,
    actionIconSize: Dp,
    isFavorite: Boolean = false,
    onShare: () -> Unit,
    onFavoriteBtnClick: () -> Unit
) {
    Row(
        modifier,
    ) {
        IconButton(
            modifier = Modifier
                .size(actionIconSize),
            onClick = { onShare() },
            content = {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    tint = Color.Gray,
                    contentDescription = "share action"
                )
            }
        )

        val transitionState = remember {
            MutableTransitionState(isFavorite).apply {
                targetState = !isFavorite
            }
        }

        IconToggleButton(
            modifier = Modifier
                .size(actionIconSize),
            checked = false,
            onCheckedChange = {
                onFavoriteBtnClick()
            },
            content = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_favorite
                    ),
                    tint = Color.Gray,
                    contentDescription = "favorite action"
                )
            }
        )
    }
}