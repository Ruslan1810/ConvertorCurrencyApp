package ru.ruslan.convertorcurrencyapp.domain.usecases

import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB

class CalculationUseCase {

    fun calculation(currency: CurrencyDB, countRub: Double): String{
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
        return String.format("%.1f", total)

    }
}