package com.amra.android.koinsample.data

import com.amra.android.koinsample.model.Currency
import com.google.gson.Gson

open class LocalDataRepositoryImpl(private val gson: Gson): DataRepository {

    override fun getCurrencies(jsonString: String): List<Currency> {
        return gson.fromJson(jsonString, Array<Currency>::class.java).toList()
    }

}