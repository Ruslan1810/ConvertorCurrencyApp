package ru.ruslan.convertorcurrencyapp.domain.repository

import androidx.lifecycle.LiveData
import ru.ruslan.convertorcurrencyapp.domain.models.modelApi.Currency

interface ApiRepository {
    fun getCurrency(): LiveData<List<Currency>>
}