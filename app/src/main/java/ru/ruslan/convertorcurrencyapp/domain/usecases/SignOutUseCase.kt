package ru.ruslan.convertorcurrencyapp.domain.usecases

import ru.ruslan.convertorcurrencyapp.domain.repository.DatabaseRepository

class SignOutUseCase(private val databaseRepository: DatabaseRepository) {
    fun signOut(){
        databaseRepository.signOut()
    }
}