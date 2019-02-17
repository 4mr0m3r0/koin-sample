package com.amra.android.koinsample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amra.android.koinsample.data.DataRepository
import com.amra.android.koinsample.data.DataRepositoryFactory
import com.amra.android.koinsample.model.Currency

class CurrenciesViewModel constructor(
    private val dataRepository: DataRepository,
    private val jsonString: String): ViewModel() {

    private val currencyLiveData = MutableLiveData<List<Currency>>()

    fun observeCurrencies(): LiveData<List<Currency>> {
        return currencyLiveData
    }

    fun retrieveCurrencies() {
        val data = dataRepository.getCurrencies(jsonString)
        currencyLiveData.postValue(data)
    }

}