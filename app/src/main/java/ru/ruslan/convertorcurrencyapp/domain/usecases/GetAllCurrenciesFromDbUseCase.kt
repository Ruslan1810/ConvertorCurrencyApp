package ru.ruslan.convertorcurrencyapp.domain.usecases

import ru.ruslan.convertorcurrencyapp.domain.repository.DatabaseRepository

class GetAllCurrenciesFromDbUseCase(databaseRepository: DatabaseRepository) {
    val allCurrenciesFromDB = databaseRepository.allCurrencies
}