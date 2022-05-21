package ru.ruslan.convertorcurrencyapp.screens.currency

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import ru.ruslan.convertorcurrencyapp.database.model.CurrencyDB

class CurrencyFragmentVieModel(application: Application) : AndroidViewModel(application) {

    fun calculation(currency: CurrencyDB, countRub: Double): String {
        val value = currency.value
        val nominal = currency.nominal
        var total = 0.0

        val currencyValue: Double = value.toDouble()
        when (nominal) {
            "1" -> total = countRub * currencyValue
            "10" -> total = currencyValue / 10 * countRub
            "100" -> total = currencyValue / 100 * countRub
            "1000" -> total = currencyValue / 1000 * countRub
            "10000" -> total = currencyValue / 10000 * countRub
        }
        Log.d("String.format", String.format("%.1f", total))
        return String.format("%.1f", total)
    }
}