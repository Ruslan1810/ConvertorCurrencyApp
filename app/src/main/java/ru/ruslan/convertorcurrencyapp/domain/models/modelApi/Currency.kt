package ru.ruslan.convertorcurrencyapp.domain.models.modelApi

data class Currency(
    var charCode: String,
    var name: String,
    var nominal: String,
    var value: String,
    var previous: String
)