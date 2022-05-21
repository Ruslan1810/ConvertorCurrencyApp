package ru.ruslan.convertorcurrencyapp.screens.listCurrencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ruslan.convertorcurrencyapp.api.ApiRepository
import ru.ruslan.convertorcurrencyapp.database.model.CurrencyDB
import ru.ruslan.convertorcurrencyapp.utils.REPOSITORY

class ListCurrenciesFragmentVieModel(application: Application): AndroidViewModel(application) {

    val allCurrenciesFromDB = REPOSITORY.allCurrencies
    val allCurrenciesFromAPI = ApiRepository().getCurrency()

    fun insertAll(list: List<CurrencyDB>, onSuccess:() -> Unit) =
        viewModelScope.launch() {
            REPOSITORY.insertAll(list){
                onSuccess()
            }
        }

    fun insert(currency: CurrencyDB) =
        viewModelScope.launch() {
            REPOSITORY.insert(currency){
            }
        }

    fun deleteAll() =
        viewModelScope.launch() {
            REPOSITORY.deleteAll()
        }


    fun signOut(){
        REPOSITORY.signOut()
    }
}