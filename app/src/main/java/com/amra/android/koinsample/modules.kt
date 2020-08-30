package com.amra.android.koinsample

import com.amra.android.koinsample.data.DataRepository
import com.amra.android.koinsample.data.LocalDataRepositoryImpl
import com.amra.android.koinsample.presentation.CurrenciesAdapter
import com.amra.android.koinsample.presentation.CurrenciesViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { Gson() }
    single { UrlHelper("https://coinmarketcap.com/currencies/") }

    factory<DataRepository> { LocalDataRepositoryImpl(get(), get()) }
}

val browseModule = module {
    factory { CurrenciesAdapter() }
    viewModel { CurrenciesViewModel(get()) }
}