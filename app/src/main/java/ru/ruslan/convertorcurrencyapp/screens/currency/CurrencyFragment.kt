package ru.ruslan.convertorcurrencyapp.screens.currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.ruslan.convertorcurrencyapp.R
import ru.ruslan.convertorcurrencyapp.database.model.CurrencyDB
import ru.ruslan.convertorcurrencyapp.databinding.FragmentCurrencyBinding
import ru.ruslan.convertorcurrencyapp.utils.getIdResource
import ru.ruslan.convertorcurrencyapp.utils.showToast

class CurrencyFragment : Fragment() {
    private lateinit var viewModel: CurrencyFragmentVieModel
    private var mBinding: FragmentCurrencyBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentCurrencyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.detailed)
        initialization()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this)[CurrencyFragmentVieModel::class.java]

        val currency = arguments?.getSerializable("currency") as CurrencyDB

        val value = currency.value
        val nominal = currency.nominal
        val charCode = currency.charCode
        var countRub = 0.0

        "$nominal $charCode = $value RUB".also { binding.currencyComparison.text = it }
        binding.codeCurrency.text = charCode
        binding.flagRus.setImageResource(R.drawable.rus)
        binding.flagCountry.setImageResource(getIdResource(charCode))

        binding.inputForRub.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var strOfInput: String = binding.inputForRub.text.toString()

                try {
                    if (strOfInput.isEmpty()) strOfInput = "0"
                    countRub = strOfInput.trim { it <= ' ' }.toDouble()

                } catch (e: NumberFormatException) {

                    if (strOfInput.isNotEmpty()) {
                        showToast("Введите число")
                        binding.inputForRub.setText(
                            strOfInput.substring(0, binding.inputForRub.length() - 1)
                        )
                        //курсор в конец строки
                        binding.inputForRub.setSelection(binding.inputForRub.text.length)
                    }
                }
                binding.outputForCurrency.text = currency.let { viewModel.calculation(it, countRub) }
            }

            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}