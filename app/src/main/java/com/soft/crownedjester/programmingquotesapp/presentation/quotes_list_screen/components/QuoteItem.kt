package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto

@Composable
fun QuoteItem(
    modifier: Modifier = Modifier,
    quote: QuotesDto
) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "${quote.quote} \n\bby ${quote.author}")
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, end = 12.dp, start = 12.dp),
        backgroundColor = Color.Gray,
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(8)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 10.dp)
        ) {
            Text(
                text = "\t${quote.quote}",
                style = MaterialTheme.typography.body2,
                fontStyle = FontStyle.Italic,
                color = Color(0x80000000)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(modifier = Modifier
                    .align(Alignment.TopStart),
                    onClick = {
                        context.startActivity(shareIntent)
                    }) {
                    Icon(imageVector = Icons.Outlined.Share, contentDescription = "Share")
                }

                Text(
                    modifier = Modifier.align(Alignment.TopEnd),
                    text = quote.author,
                    style = MaterialTheme.typography.body2,
                    fontStyle = FontStyle.Normal
                )
            }
        }
    }
}