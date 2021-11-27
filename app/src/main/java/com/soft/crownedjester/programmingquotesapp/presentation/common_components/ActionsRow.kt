package com.soft.crownedjester.programmingquotesapp.presentation.common_components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
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
        val iconTintColor = if (isSystemInDarkTheme()) Color.White else Color.Gray
        IconButton(
            modifier = Modifier
                .size(actionIconSize),
            onClick = { onShare() },
            content = {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    tint = iconTintColor,
                    contentDescription = "share action"
                )
            }
        )

        IconToggleButton(
            modifier = Modifier
                .size(actionIconSize),
            checked = isFavorite,
            onCheckedChange = {
                onFavoriteBtnClick()
            },
            content = {
                Icon(
                    painter = if (isFavorite)
                        painterResource(
                            id = R.drawable.ic_favorite_filled
                        ) else {
                        painterResource(id = R.drawable.ic_favorite)
                    },
                    tint = if (isFavorite) Color.Red else iconTintColor,
                    contentDescription = "favorite action"
                )
            }
        )
    }
}