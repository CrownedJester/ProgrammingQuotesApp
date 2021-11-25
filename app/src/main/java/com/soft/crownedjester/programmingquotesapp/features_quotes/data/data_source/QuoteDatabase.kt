package com.soft.crownedjester.programmingquotesapp.features_quotes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote

@Database(
    entities = [Quote::class],
    version = 1
)
abstract class QuoteDatabase : RoomDatabase() {

    abstract val quoteDao: QuoteDao

}