package ru.ruslan.convertorcurrencyapp.domain.usecases

import ru.ruslan.convertorcurrencyapp.domain.repository.DatabaseRepository
import ru.ruslan.convertorcurrencyapp.utils.REPOSITORY

class DeleteAllUseCase(private val databaseRepository: DatabaseRepository)  {

    suspend fun deleteAll() = databaseRepository.deleteAll()
}