package ru.ruslan.convertorcurrencyapp.domain.usecases

import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB
import ru.ruslan.convertorcurrencyapp.domain.repository.DatabaseRepository

class InsertAllUseCase(private val databaseRepository: DatabaseRepository) {
        suspend fun insertAll(list: List<CurrencyDB>) =
        databaseRepository.insertAll(list)
}