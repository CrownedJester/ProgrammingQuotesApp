package com.soft.crownedjester.programmingquotesapp.di

import android.app.Application
import androidx.room.Room
import com.soft.crownedjester.programmingquotesapp.common.Constants
import com.soft.crownedjester.programmingquotesapp.data.remote.IProgrammingQuotesApi
import com.soft.crownedjester.programmingquotesapp.domain.repository.QuotesApiServicesRepository
import com.soft.crownedjester.programmingquotesapp.domain.repository.QuotesApiServicesRepositoryImpl
import com.soft.crownedjester.programmingquotesapp.domain.use_case.QuoteUseCases
import com.soft.crownedjester.programmingquotesapp.domain.use_case.add_to_favorite.AddQuoteToFavorite
import com.soft.crownedjester.programmingquotesapp.domain.use_case.get_favorite_quotes.GetFavoriteQuotes
import com.soft.crownedjester.programmingquotesapp.domain.use_case.get_quotes.GetQuotes
import com.soft.crownedjester.programmingquotesapp.domain.use_case.remove_favorite.RemoveQuoteFromFavorite
import com.soft.crownedjester.programmingquotesapp.domain.use_case.share_quote.ShareQuote
import com.soft.crownedjester.programmingquotesapp.features_quotes.data.data_source.QuoteDatabase
import com.soft.crownedjester.programmingquotesapp.features_quotes.repository.QuoteDatabaseRepository
import com.soft.crownedjester.programmingquotesapp.features_quotes.repository.QuoteDatabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteDatabase(application: Application): QuoteDatabase {
        return Room.databaseBuilder(
            application,
            QuoteDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuotesDatabaseRepository(
        db: QuoteDatabase
    ): QuoteDatabaseRepository {
        return QuoteDatabaseRepositoryImpl(db.quoteDao)
    }

    @Provides
    @Singleton
    fun provideQuotesApi(): IProgrammingQuotesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.QUOTES_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IProgrammingQuotesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuotesRepository(
        api: IProgrammingQuotesApi
    ): QuotesApiServicesRepository {
        return QuotesApiServicesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideQuoteUseCases(
        dbRepository: QuoteDatabaseRepository,
        apiServicesRepository: QuotesApiServicesRepository
    ): QuoteUseCases {
        return QuoteUseCases(
            GetQuotes(apiServicesRepository),
            GetFavoriteQuotes(dbRepository),
            AddQuoteToFavorite(dbRepository),
            RemoveQuoteFromFavorite(dbRepository),
            ShareQuote()
        )
    }

}