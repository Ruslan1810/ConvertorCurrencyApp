package ru.ruslan.convertorcurrencyapp.presentation.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.ruslan.convertorcurrencyapp.domain.usecases.ConnectToDatabaseUseCase

class StartFragmentViewModel(application: Application): AndroidViewModel(application){
    private val context = application

    private val connectToDatabaseUseCase = ConnectToDatabaseUseCase(context)

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        connectToDatabaseUseCase.initDatabase(type,onSuccess)
    }
}