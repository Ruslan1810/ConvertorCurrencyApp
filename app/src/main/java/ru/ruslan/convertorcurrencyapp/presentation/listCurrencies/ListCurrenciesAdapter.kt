package ru.ruslan.convertorcurrencyapp.presentation.listCurrencies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item.view.*
import ru.ruslan.convertorcurrencyapp.R
import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB
import ru.ruslan.convertorcurrencyapp.utils.getIdResource
import java.util.*
import kotlin.collections.ArrayList

class ListCurrenciesAdapter(private var listCurrencies: List<CurrencyDB>) :
    RecyclerView.Adapter<ListCurrenciesAdapter.Holder>(), Filterable {

    private var filterListCurrencies = emptyList<CurrencyDB>()

    init {
        filterListCurrencies = listCurrencies
    }


    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val charCode: TextView = view.currency_char_code
        val name: TextView = view.currency_name
        val nominal: TextView = view.currency_nominal
        val value: TextView = view.currency_value
        val arrow: ImageView = view.arrow
        val flag: ImageView = view.flag_country
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currency = filterListCurrencies[position]
        holder.charCode.text = currency.charCode
        holder.name.text = currency.name
        holder.nominal.text = currency.nominal
        holder.value.text = currency.value
        holder.flag.setImageResource(getIdResource(currency.charCode))

        if (currency.value.toDouble() > currency.previous.toDouble()) {
            holder.arrow.setImageResource(R.drawable.arrow_up)
        } else {
            holder.arrow.setImageResource(R.drawable.arrow_down)
        }
    }

    override fun getItemCount(): Int = filterListCurrencies.size

    override fun onViewAttachedToWindow(holder: Holder) {
        holder.itemView.setOnClickListener {
            ListCurrenciesFragment.clickItem(filterListCurrencies[holder.absoluteAdapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: Holder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterListCurrencies = listCurrencies
                } else {
                    val resultList = ArrayList<CurrencyDB>()
                    for (row in listCurrencies) {
                        if (row.name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    filterListCurrencies = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterListCurrencies
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterListCurrencies = results?.values as ArrayList<CurrencyDB>
                notifyDataSetChanged()
            }

        }
    }
}