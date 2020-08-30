package com.amra.android.koinsample.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.amra.android.koinsample.R
import com.amra.android.koinsample.UrlHelper
import com.amra.android.koinsample.model.Currency
import kotlinx.android.synthetic.main.view_currency.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class CurrencyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr), KoinComponent {

    val urlHelper: UrlHelper by inject()

    init {
        View.inflate(context, R.layout.view_currency, this)
    }

    fun setCurrency(currency: Currency) {
        text_name.text = currency.name
        text_symbol.text = currency.symbol

        setOnClickListener {
            urlHelper.launchCurrencyUrl(context,currency.slug)
        }
    }

}