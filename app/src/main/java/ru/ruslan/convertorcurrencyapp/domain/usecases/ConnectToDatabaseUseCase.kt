package ru.ruslan.convertorcurrencyapp.domain.usecases

import android.content.Context
import ru.ruslan.convertorcurrencyapp.data.database.room.AppRoomDatabase
import ru.ruslan.convertorcurrencyapp.data.repository.AppFirebaseRepository
import ru.ruslan.convertorcurrencyapp.data.repository.AppRoomRepository
import ru.ruslan.convertorcurrencyapp.utils.REPOSITORY
import ru.ruslan.convertorcurrencyapp.utils.TYPE_FIREBASE
import ru.ruslan.convertorcurrencyapp.utils.TYPE_ROOM
import ru.ruslan.convertorcurrencyapp.utils.showToast

class ConnectToDatabaseUseCase(private val context: Context)  {
    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({onSuccess()},{ showToast(it) })
            }
        }
    }
}