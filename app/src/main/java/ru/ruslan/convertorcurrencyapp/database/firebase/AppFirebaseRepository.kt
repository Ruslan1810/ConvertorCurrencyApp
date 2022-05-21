package ru.ruslan.convertorcurrencyapp.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.ruslan.convertorcurrencyapp.database.DatabaseRepository
import ru.ruslan.convertorcurrencyapp.database.model.CurrencyDB
import ru.ruslan.convertorcurrencyapp.utils.*

class AppFirebaseRepository : DatabaseRepository {
    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allCurrencies: LiveData<List<CurrencyDB>> = AllCurrenciesLiveData()

    override suspend fun insertAll(list: List<CurrencyDB>, onSuccess: () -> Unit) {
        for(currency in list) insert(currency){}
    }

    override suspend fun insert(currency: CurrencyDB, onSuccess: () -> Unit) {
        val mapNote = hashMapOf<String, Any>()
        mapNote[NAME] = currency.name
        mapNote[CHARCODE] = currency.charCode
        mapNote[VALUE ] = currency.value
        mapNote[PREVIOUS] = currency.previous
        mapNote[NOMINAL] = currency.nominal

        REF_DATABASE.child(currency.name)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener() {
                AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString())
                    Log.e("ErrorFirebase", it.message.toString())}
            }

        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)

    }

    override suspend fun delete(currency: CurrencyDB, onSuccess: () -> Unit) {}

    override suspend fun deleteAll() {}

    override fun signOut() {
        AUTH.signOut()
    }
}