package ru.ruslan.convertorcurrencyapp.data.repository

import androidx.lifecycle.LiveData
import ru.ruslan.convertorcurrencyapp.domain.repository.DatabaseRepository
import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB
import ru.ruslan.convertorcurrencyapp.data.database.room.AppRoomDao

class AppRoomRepository(private val appRoomDao: AppRoomDao): DatabaseRepository {
    override val allCurrencies: LiveData<List<CurrencyDB>>
        get() = appRoomDao.getAllCurrencies()


    override suspend fun insertAll(list: List<CurrencyDB>) {
        appRoomDao.insertAll(list)
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