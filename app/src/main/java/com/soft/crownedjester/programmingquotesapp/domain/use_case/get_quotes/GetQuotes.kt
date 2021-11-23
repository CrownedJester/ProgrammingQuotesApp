package com.soft.crownedjester.programmingquotesapp.domain.use_case.get_quotes

import com.soft.crownedjester.programmingquotesapp.common.Resource
import com.soft.crownedjester.programmingquotesapp.data.remote.dto.QuotesDto
import com.soft.crownedjester.programmingquotesapp.data.remote.dto.toQuote
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote
import com.soft.crownedjester.programmingquotesapp.domain.repository.IQuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQuotes @Inject constructor(
    private val repository: IQuotesRepository
) {

    operator fun invoke(): Flow<Resource<List<Quote>>> = flow {
        try {
            emit(Resource.Loading())

            val quotes = repository.getQuotes().map{ quote ->
                quote.toQuote()
            }

            emit(Resource.Success(quotes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}