package com.amra.android.koinsample


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.amra.android.koinsample.data.DataRepository
import com.amra.android.koinsample.model.Currency
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : KoinTest {

    val mockDataRepository: DataRepository by inject()

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        declareMock<DataRepository>()
    }

    @Test
    fun useAppContext() {
        activity.launchActivity(null)
        onView(withId(R.id.rv_currencies)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCurrenciesDisplay() {
        val currencies = CurrencyFactory.makeCurrencyList(5)
        stubDataRepositoryGetCurrencies(currencies)
        activity.launchActivity(null)

        checkCurrenciesAreDisplayed(currencies)
    }

    private fun checkCurrenciesAreDisplayed(currencies: List<Currency>) {
        currencies.forEachIndexed { index, currency ->
            onView(withId(R.id.rv_currencies))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
            onView(RecyclerViewMatcher.withRecyclerView(R.id.rv_currencies).atPosition(index))
                .check(matches(hasDescendant(withText(currency.name))))
        }
    }

    private fun stubDataRepositoryGetCurrencies(currencies: List<Currency>) {
        whenever(mockDataRepository.getCurrencies(any())).thenReturn(currencies)
    }

}
