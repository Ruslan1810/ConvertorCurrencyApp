package ru.ruslan.convertorcurrencyapp.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.ruslan.convertorcurrencyapp.database.firebase.AppFirebaseRepository
import ru.ruslan.convertorcurrencyapp.database.room.AppRoomDatabase
import ru.ruslan.convertorcurrencyapp.database.room.AppRoomRepository
import ru.ruslan.convertorcurrencyapp.utils.REPOSITORY
import ru.ruslan.convertorcurrencyapp.utils.TYPE_FIREBASE
import ru.ruslan.convertorcurrencyapp.utils.TYPE_ROOM
import ru.ruslan.convertorcurrencyapp.utils.showToast

class StartFragmentViewModel(application: Application): AndroidViewModel(application){
    private val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
               REPOSITORY = AppFirebaseRepository()
               REPOSITORY.connectToDatabase({onSuccess()},{ showToast(it)})
            }
        }
    }
}