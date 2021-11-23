package com.soft.crownedjester.programmingquotesapp.domain.model

import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.soft.crownedjester.programmingquotesapp.presentation.ui.theme.*

@Entity(tableName = "quote_table")
data class Quote(
    @PrimaryKey
    val id: String,
    val quote: String,
    val author: String,
    val color: Int = LightYellow.toArgb()
) {
    companion object {
        val quoteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink, LightYellow)
    }
}