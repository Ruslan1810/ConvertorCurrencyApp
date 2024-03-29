package ru.ruslan.convertorcurrencyapp.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.ruslan.convertorcurrencyapp.domain.models.modelDb.CurrencyDB

@Database(entities = [CurrencyDB::class], version = 3)
abstract class AppRoomDatabase:RoomDatabase() {

    abstract fun getAppRoomDao(): AppRoomDao

    companion object{

        @Volatile
        private var database: AppRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppRoomDatabase {
            return if(database == null){
                database = Room.databaseBuilder(
                    context, AppRoomDatabase::class.java, "database"
                ).fallbackToDestructiveMigration().build()
                database as AppRoomDatabase
            }else database as AppRoomDatabase
        }
    }
}