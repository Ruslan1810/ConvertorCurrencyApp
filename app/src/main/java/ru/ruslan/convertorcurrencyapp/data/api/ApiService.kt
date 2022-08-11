package ru.ruslan.convertorcurrencyapp.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import ru.ruslan.convertorcurrencyapp.domain.models.modelApi.Currency
import ru.ruslan.convertorcurrencyapp.utils.APP_ACTIVITY
import ru.ruslan.convertorcurrencyapp.utils.JSON_URL
import ru.ruslan.convertorcurrencyapp.utils.showToast

class ApiService {

    private var listOfCurrency: MutableList<Currency> = mutableListOf()

    fun getCurrency(): LiveData<List<Currency>> {
        val setCurrency: MutableLiveData<List<Currency>> = MutableLiveData<List<Currency>>()

        val stringReq = StringRequest(
            Request.Method.GET, JSON_URL,
            { response ->
                try {
                    val jsonObj = JSONObject(response)
                    val jSonValute = jsonObj.getJSONObject("Valute")
                    val arrayKey = jSonValute.keys()
                    while (arrayKey.hasNext()) {
                        val key = arrayKey.next()
                        val jSonItem = jSonValute.getJSONObject(key)
                        val currency = Currency(
                            jSonItem.getString("CharCode"),
                            jSonItem.getString("Name"),
                            jSonItem.getString("Nominal"),
                            jSonItem.getString("Value"),
                            jSonItem.getString("Previous")
                        )
                        listOfCurrency.add(currency)

                    }
                    setCurrency.postValue(listOfCurrency)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
        )
        { error ->
            showToast(error.message!!)
        }
        val queue = Volley.newRequestQueue(APP_ACTIVITY.application)
        queue.add(stringReq)
        return setCurrency
    }
}