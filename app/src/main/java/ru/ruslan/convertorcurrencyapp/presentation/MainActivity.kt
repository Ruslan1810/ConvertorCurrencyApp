package ru.ruslan.convertorcurrencyapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.ruslan.convertorcurrencyapp.R
import ru.ruslan.convertorcurrencyapp.data.database.room.AppRoomDatabase
import ru.ruslan.convertorcurrencyapp.data.repository.AppRoomRepository
import ru.ruslan.convertorcurrencyapp.databinding.ActivityMainBinding
import ru.ruslan.convertorcurrencyapp.utils.APP_ACTIVITY
import ru.ruslan.convertorcurrencyapp.utils.REPOSITORY

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar

        setSupportActionBar(mToolbar)

        APP_ACTIVITY = this
        val dao = AppRoomDatabase.getInstance(this).getAppRoomDao()
        REPOSITORY = AppRoomRepository(dao)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}