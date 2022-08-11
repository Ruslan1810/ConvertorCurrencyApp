package ru.ruslan.convertorcurrencyapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import ru.ruslan.convertorcurrencyapp.data.api.ApiService
import ru.ruslan.convertorcurrencyapp.utils.APP_ACTIVITY
import ru.ruslan.convertorcurrencyapp.domain.models.modelApi.Currency
import ru.ruslan.convertorcurrencyapp.domain.repository.ApiRepository
import ru.ruslan.convertorcurrencyapp.utils.JSON_URL
import ru.ruslan.convertorcurrencyapp.utils.showToast

class ApiRepositoryImpl(private val apiService: ApiService): ApiRepository {

    override fun getCurrency(): LiveData<List<Currency>> {
        return apiService.getCurrency()
    }
}