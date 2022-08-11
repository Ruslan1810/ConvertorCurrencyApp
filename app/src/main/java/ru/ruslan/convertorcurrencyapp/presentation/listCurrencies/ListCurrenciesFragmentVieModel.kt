package ru.ruslan.convertorcurrencyapp.presentation.listCurrencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ruslan.convertorcurrencyapp.data.api.ApiService
import ru.ruslan.convertorcurrencyapp.data.repository.ApiRepositoryImpl
import ru.ruslan.convertorcurrencyapp.databinding.FragmentListCurrenciesBinding
import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB
import ru.ruslan.convertorcurrencyapp.domain.usecases.*
import ru.ruslan.convertorcurrencyapp.utils.REPOSITORY

class ListCurrenciesFragmentVieModel: ViewModel() {

    private val insertAllUseCase = InsertAllUseCase(REPOSITORY)
    private val insertUseCase = InsertUseCase(REPOSITORY)
    private val deleteAllUseCase = DeleteAllUseCase(REPOSITORY)
    private val signOutUseCase = SignOutUseCase(REPOSITORY)
    private val getAllCurrenciesFromDbUseCase = GetAllCurrenciesFromDbUseCase(REPOSITORY)
    private val getAllCurrenciesFromApiUseCase = GetAllCurrenciesFromApiUseCase(
        ApiRepositoryImpl(ApiService())
    )
    private val initSearchUseCase = InitSearchUseCase()

    val allCurrenciesFromDB = getAllCurrenciesFromDbUseCase.allCurrenciesFromDB
    val allCurrenciesFromAPI = getAllCurrenciesFromApiUseCase.allCurrenciesFromAPI

    fun insertAll(list: List<CurrencyDB>){
        viewModelScope.launch {
            insertAllUseCase.insertAll(list)
        }
    }

    fun insert(currency: CurrencyDB) =
        viewModelScope.launch() {
            insertUseCase.insert(currency)
        }

    fun deleteAll() =
        viewModelScope.launch() {
            deleteAllUseCase.deleteAll()
        }


    fun signOut(){
        signOutUseCase.signOut()
    }

    fun initSearch(binding: FragmentListCurrenciesBinding, mAdapter: ListCurrenciesAdapter){
        initSearchUseCase.initSearch(binding, mAdapter)
    }
}