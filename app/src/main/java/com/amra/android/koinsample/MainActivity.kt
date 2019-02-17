package com.amra.android.koinsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.amra.android.koinsample.presentation.CurrenciesAdapter
import com.amra.android.koinsample.presentation.CurrenciesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    val currenciesAdapter: CurrenciesAdapter by inject()
    val currenciesViewModel: CurrenciesViewModel by viewModel {
        val currenciesJson = resources.openRawResource(R.raw.currencies).bufferedReader().use { it.readText() }
        parametersOf(currenciesJson)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindScope(getOrCreateScope("browse"))

        setupCurrenciesRecycler()

        currenciesViewModel.observeCurrencies().observe(this, Observer {
            it?.let {
                currenciesAdapter.currencies = it
            }
        })

        currenciesViewModel.retrieveCurrencies()
    }

    private fun setupCurrenciesRecycler() {
        rv_currencies.layoutManager = LinearLayoutManager(this)
        rv_currencies.adapter = currenciesAdapter
    }
}
