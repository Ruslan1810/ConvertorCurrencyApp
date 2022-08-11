package ru.ruslan.convertorcurrencyapp.domain.repository

import androidx.lifecycle.LiveData
import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB

interface DatabaseRepository {
    val allCurrencies: LiveData<List<CurrencyDB>>

    suspend fun insertAll(list:List<CurrencyDB>)
    suspend fun insert(currency:CurrencyDB, onSuccess:() -> Unit)
    suspend fun delete(currency:CurrencyDB, onSuccess:() -> Unit)
    suspend fun deleteAll()

    fun connectToDatabase(onSuccess: () -> Unit,onFail: (String) -> Unit) {}
    fun signOut(){}
}