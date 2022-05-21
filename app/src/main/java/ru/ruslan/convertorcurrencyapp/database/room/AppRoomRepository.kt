package ru.ruslan.convertorcurrencyapp.database.room

import androidx.lifecycle.LiveData
import ru.ruslan.convertorcurrencyapp.database.DatabaseRepository
import ru.ruslan.convertorcurrencyapp.database.model.CurrencyDB

class AppRoomRepository(private val appRoomDao: AppRoomDao): DatabaseRepository {
    override val allCurrencies: LiveData<List<CurrencyDB>>
        get() = appRoomDao.getAllCurrencies()


    override suspend fun insertAll(list: List<CurrencyDB>, onSuccess: () -> Unit) {
        appRoomDao.insertAll(list)
        onSuccess()
    }

    override suspend fun insert(currency: CurrencyDB, onSuccess: () -> Unit) {
        appRoomDao.insert(currency)
        onSuccess()
    }

    override suspend fun delete(currency: CurrencyDB, onSuccess: () -> Unit) {
        appRoomDao.delete(currency)
        onSuccess()
    }

    override suspend fun deleteAll() {
        appRoomDao.deleteAll()
    }


    override fun signOut() {
        super.signOut()
    }
}