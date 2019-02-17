package com.amra.android.koinsample

import com.amra.android.koinsample.data.DataRepository
import com.amra.android.koinsample.data.DataRepositoryFactory
import com.amra.android.koinsample.data.LocalDataRepositoryImpl
import com.amra.android.koinsample.data.RemoteDataRepositoryImpl
import com.amra.android.koinsample.presentation.CurrenciesAdapter
import com.amra.android.koinsample.presentation.CurrenciesViewModel
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single { Gson() }
    single { UrlHelper(getProperty("currency_base_url")) }

    factory<DataRepository> { LocalDataRepositoryImpl(get()) }
}

val browseModule = module("browse") {
    factory { CurrenciesAdapter() }
    viewModel { (jsonString: String) -> CurrenciesViewModel(get(), jsonString) }
}