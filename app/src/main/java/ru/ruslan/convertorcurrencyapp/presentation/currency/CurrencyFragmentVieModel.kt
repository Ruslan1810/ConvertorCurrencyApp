package ru.ruslan.convertorcurrencyapp.presentation.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB
import ru.ruslan.convertorcurrencyapp.domain.usecases.CalculationUseCase

class CurrencyFragmentVieModel : ViewModel() {

    var result = MutableLiveData<String>()
    private val calculationUseCase = CalculationUseCase()

    fun calculation(currency: CurrencyDB, countRub: Double) {
        result.value = calculationUseCase.calculation(currency, countRub)
    }

}