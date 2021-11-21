package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
    onRemoveFavorite: () -> Unit,
    onShare: () -> Unit,
    onFavorite: () -> Unit
) {
    Row(
        modifier,
    ) {
        IconButton(
            modifier = Modifier
                .size(actionIconSize),
            onClick = { onRemoveFavorite() },
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove),
                    tint = Color.Gray,
                    contentDescription = "delete action"
                )
            }
        )
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
        IconButton(modifier = Modifier
            .size(actionIconSize),
            onClick = { onFavorite() },
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    tint = Color.Gray,
                    contentDescription = "favorite action"
                )
            }
        )
    }

}