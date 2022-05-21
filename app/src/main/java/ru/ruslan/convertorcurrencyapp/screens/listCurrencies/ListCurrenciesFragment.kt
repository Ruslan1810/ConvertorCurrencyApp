package ru.ruslan.convertorcurrencyapp.screens.listCurrencies

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.ruslan.convertorcurrencyapp.R
import ru.ruslan.convertorcurrencyapp.api.model.Currency
import ru.ruslan.convertorcurrencyapp.database.model.CurrencyDB
import ru.ruslan.convertorcurrencyapp.databinding.FragmentListCurrenciesBinding
import ru.ruslan.convertorcurrencyapp.utils.APP_ACTIVITY
import ru.ruslan.convertorcurrencyapp.utils.transformation

class ListCurrenciesFragment : Fragment() {

    private lateinit var viewModel: ListCurrenciesFragmentVieModel
    private var mBinding: FragmentListCurrenciesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ListCurrenciesAdapter
    private lateinit var mObserverListFromDB: Observer<List<CurrencyDB>>
    private lateinit var mObserverListFromApi: Observer<List<Currency>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            FragmentListCurrenciesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        fun clickItem(currency: CurrencyDB) {
            val bundle = Bundle()
            bundle.putSerializable("currency", currency)
            APP_ACTIVITY.navController.navigate(
                R.id.action_listCurrenciesFragment_to_currencyFragment,
                bundle
            )
        }
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this)[ListCurrenciesFragmentVieModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = "Все валюты"

        initSearch()

        mObserverListFromApi = Observer { it ->
            val listCustomer = it.map { it.transformation() }
            viewModel.deleteAll()
            viewModel.insertAll(listCustomer) {}
        }

        mObserverListFromDB = Observer {
            mAdapter = ListCurrenciesAdapter(it)
            mRecyclerView = binding.recyclerView
            mRecyclerView.adapter = mAdapter

        }
        setHasOptionsMenu(true)

        viewModel.allCurrenciesFromAPI.observe(this, mObserverListFromApi)
        viewModel.allCurrenciesFromDB.observe(this, mObserverListFromDB)

    }

    private fun initSearch() {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                viewModel.signOut()
                APP_ACTIVITY.navController.navigate(R.id.action_listCurrenciesFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        viewModel.allCurrenciesFromDB.removeObserver(mObserverListFromDB)
        mRecyclerView.adapter = null
    }
}