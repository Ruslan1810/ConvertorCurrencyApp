package ru.ruslan.convertorcurrencyapp.data.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB

@Dao
interface AppRoomDao {

    @Query("SELECT * from currencies_table")
    fun getAllCurrencies(): LiveData<List<CurrencyDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(currency: CurrencyDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<CurrencyDB>)

    @Delete
    suspend fun delete(currency: CurrencyDB)

    @Query("DELETE  from currencies_table")
    suspend fun deleteAll()

}