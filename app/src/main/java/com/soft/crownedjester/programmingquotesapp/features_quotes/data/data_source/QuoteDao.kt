package com.soft.crownedjester.programmingquotesapp.features_quotes.data.data_source

import androidx.room.*
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table")
    fun getFavoriteQuotes(): Flow<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)
}