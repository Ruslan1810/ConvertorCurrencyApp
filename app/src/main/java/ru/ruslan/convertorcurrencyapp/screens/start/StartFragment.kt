package ru.ruslan.convertorcurrencyapp.screens.start

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.ruslan.convertorcurrencyapp.R
import ru.ruslan.convertorcurrencyapp.databinding.FragmentStartBinding
import ru.ruslan.convertorcurrencyapp.utils.*

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        mViewModel = ViewModelProvider(this)[StartFragmentViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.start_label)
        initialization()
    }

    @SuppressLint("SetTextI18n")
    private fun initialization() {
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_listCurrenciesFragment)
            }
        }
        mBinding.btnFirebase.setOnClickListener {

            mBinding.inputEmail.visibility = View.VISIBLE
            mBinding.inputPassword.visibility = View.VISIBLE
            mBinding.btnLogin.visibility = View.VISIBLE

            mBinding.inputEmail.setText("tt@t.ru")
            mBinding.inputPassword.setText("123456")

            mBinding.btnLogin.setOnClickListener {
                val inputEmail = mBinding.inputEmail.text.toString()
                val inputPassword = mBinding.inputPassword.text.toString()
                if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    mViewModel.initDatabase(TYPE_FIREBASE) {
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_listCurrenciesFragment)
                    }
                } else {
                    showToast(getString(R.string.toast_wrong_enter))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}