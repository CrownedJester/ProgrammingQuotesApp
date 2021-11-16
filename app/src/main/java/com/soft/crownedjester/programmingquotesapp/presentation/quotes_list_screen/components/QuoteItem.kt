package com.soft.crownedjester.programmingquotesapp.presentation.quotes_list_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
fun QuoteItem(
    modifier: Modifier = Modifier,
    quote: QuotesDto
) {

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
                .padding(4.dp)
        ) {
//            IconButton(onClick = {  }) {
//                Icon(imageVector = Icons.Sharp., contentDescription = )
//
//            }
            Text(
                text = "\t${quote.quote}",
                style = MaterialTheme.typography.body2,
                fontStyle = FontStyle.Italic,
                color = Color(0x80000000)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, bottom = 4.dp, top = 2.dp)

            ) {
                Text(
                    text = quote.author,
                    style = MaterialTheme.typography.body2,
                    fontStyle = FontStyle.Normal
                )
            }

        }
    }
}