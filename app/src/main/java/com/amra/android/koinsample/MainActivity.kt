package com.amra.android.koinsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.amra.android.koinsample.presentation.CurrenciesAdapter
import com.amra.android.koinsample.presentation.CurrenciesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val currenciesAdapter: CurrenciesAdapter by inject()
    val currenciesViewModel: CurrenciesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
