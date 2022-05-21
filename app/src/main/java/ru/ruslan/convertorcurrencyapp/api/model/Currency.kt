package ru.ruslan.convertorcurrencyapp.api.model

data class Currency(
    var charCode: String,
    var name: String,
    var nominal: String,
    var value: String,
    var previous: String
)