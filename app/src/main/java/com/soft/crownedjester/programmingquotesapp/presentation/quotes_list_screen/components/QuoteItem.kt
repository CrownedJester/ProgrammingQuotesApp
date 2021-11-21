package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto

@Composable
fun QuoteItem(quote: QuotesDto, count: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 10.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 8.dp),
            text = "Quote #$count",
            style = MaterialTheme.typography.body1
        )
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
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = quote.author,
                style = MaterialTheme.typography.body2,
                fontStyle = FontStyle.Normal
            )
        }
    }
}