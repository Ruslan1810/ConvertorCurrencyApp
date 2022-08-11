package ru.ruslan.convertorcurrencyapp.domain.usecases

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import ru.ruslan.convertorcurrencyapp.databinding.FragmentListCurrenciesBinding
import ru.ruslan.convertorcurrencyapp.presentation.listCurrencies.ListCurrenciesAdapter

class InitSearchUseCase {

    fun initSearch(binding: FragmentListCurrenciesBinding, mAdapter: ListCurrenciesAdapter){
        val searchIcon =
            binding.currencySearch.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)
        val cancelIcon =
            binding.currencySearch.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)
        val textView =
            binding.currencySearch.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.WHITE)

        binding.currencySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter.filter.filter(newText)
                return false
            }
        })
    }
}