package ru.ruslan.convertorcurrencyapp.utils

import android.widget.Toast
import ru.ruslan.convertorcurrencyapp.api.model.Currency
import ru.ruslan.convertorcurrencyapp.database.model.CurrencyDB

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun getIdResource(charCode: String): Int {
    var nameResource = charCode.lowercase()
    if (charCode == "TRY") nameResource = "tur"

    return APP_ACTIVITY.resources.getIdentifier(
        nameResource, "drawable",
        APP_ACTIVITY.packageName
    )
}

fun Currency.transformation() = CurrencyDB(
    charCode = charCode,
    name = name,
    nominal = nominal,
    value = value,
    previous = previous,
)