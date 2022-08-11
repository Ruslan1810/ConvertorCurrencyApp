package ru.ruslan.convertorcurrencyapp.domain.usecases

import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB
import ru.ruslan.convertorcurrencyapp.domain.repository.DatabaseRepository

class InsertUseCase(private val databaseRepository: DatabaseRepository)  {
    suspend fun insert(currency: CurrencyDB) =
        databaseRepository.insert(currency){
        }
}