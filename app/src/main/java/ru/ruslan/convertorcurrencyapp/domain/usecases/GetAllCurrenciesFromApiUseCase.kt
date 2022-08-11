package ru.ruslan.convertorcurrencyapp.domain.usecases

import ru.ruslan.convertorcurrencyapp.domain.repository.ApiRepository

class GetAllCurrenciesFromApiUseCase(apiRepository: ApiRepository) {
    val allCurrenciesFromAPI = apiRepository.getCurrency()
}