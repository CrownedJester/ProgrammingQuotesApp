package com.soft.crownedjester.programmingquotesapp.domain.use_case.share_quote

import android.content.Context
import android.content.Intent
import com.soft.crownedjester.programmingquotesapp.domain.model.Quote

class ShareQuote {

    suspend operator fun invoke(quote: Quote, context: Context) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${quote.quote} \n\t\t\t\tby ${quote.author}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

}