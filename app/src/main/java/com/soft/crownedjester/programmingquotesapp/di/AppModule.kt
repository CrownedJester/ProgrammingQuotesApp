package com.soft.crownedjester.programmingquotesapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.soft.crownedjester.programmingquotesapp.common.Constants
import com.soft.crownedjester.programmingquotesapp.data.remote.IProgrammingQuotesApi
import com.soft.crownedjester.programmingquotesapp.domain.repository.QuotesApiServicesRepository
import com.soft.crownedjester.programmingquotesapp.domain.repository.QuotesApiServicesRepositoryImpl
import com.soft.crownedjester.programmingquotesapp.features_quotes.data.data_source.QuoteDao
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
    fun provideQuoteDatabase(application: Application): RoomDatabase {
        return Room.databaseBuilder(
            application,
            QuoteDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuotesDatabaseRepository(
        quoteDao: QuoteDao
    ): QuoteDatabaseRepository {
        return QuoteDatabaseRepositoryImpl(quoteDao)
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

}