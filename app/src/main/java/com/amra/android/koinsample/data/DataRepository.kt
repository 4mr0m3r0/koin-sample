package com.amra.android.koinsample.data

import com.amra.android.koinsample.model.Currency

interface DataRepository {

    fun getCurrencies(jsonString: String): List<Currency>

}