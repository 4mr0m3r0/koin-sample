package com.amra.android.koinsample.data

import android.content.Context
import com.amra.android.koinsample.R
import com.amra.android.koinsample.model.Currency
import com.google.gson.Gson

open class LocalDataRepositoryImpl(private val gson: Gson,
                                   private val context: Context): DataRepository {

    override fun getCurrencies(): List<Currency> {
        val jsonString = context
            .resources
            .openRawResource(R.raw.currencies)
            .bufferedReader()
            .use { it.readText() }
        return gson.fromJson(jsonString, Array<Currency>::class.java).toList()
    }

}