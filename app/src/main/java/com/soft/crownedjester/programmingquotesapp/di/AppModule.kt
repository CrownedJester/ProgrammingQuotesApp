package com.soft.crownedjester.programmingquotesapp.di

import com.soft.crownedjester.programmingquotesapp.common.Constants
import com.soft.crownedjester.programmingquotesapp.data.remote.IProgrammingQuotesApi
import com.soft.crownedjester.programmingquotesapp.domain.repository.IQuotesRepository
import com.soft.crownedjester.programmingquotesapp.domain.repository.QuotesRepositoryImpl
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
    ): IQuotesRepository {
        return QuotesRepositoryImpl(api)
    }

}