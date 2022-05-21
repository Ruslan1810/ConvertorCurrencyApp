package ru.ruslan.convertorcurrencyapp.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ru.ruslan.convertorcurrencyapp.database.model.CurrencyDB
import ru.ruslan.convertorcurrencyapp.utils.REF_DATABASE

class AllCurrenciesLiveData: LiveData<List<CurrencyDB>> () {

    private val listener = object : ValueEventListener{
        override fun onCancelled(dataSnapshot: DatabaseError) {}

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            value = dataSnapshot.children.map {
                it.getValue(CurrencyDB::class.java)?:CurrencyDB()
            }
        }
    }

    override fun onInactive() {
        REF_DATABASE.removeEventListener(listener)
        super.onInactive()
    }

    override fun onActive() {
        REF_DATABASE.addValueEventListener(listener)
        super.onActive()
    }
}