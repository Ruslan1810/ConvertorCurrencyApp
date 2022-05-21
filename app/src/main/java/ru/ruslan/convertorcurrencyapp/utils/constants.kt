package ru.ruslan.convertorcurrencyapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import ru.ruslan.convertorcurrencyapp.screens.MainActivity
import ru.ruslan.convertorcurrencyapp.database.DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository
const val TYPE_FIREBASE = "type_firebase"
const val TYPE_ROOM = "type_room"
const val JSON_URL = "https://www.cbr-xml-daily.ru/daily_json.js"
lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_ID:String
lateinit var REF_DATABASE: DatabaseReference
const val TYPE_DATABASE = "type_database"
lateinit var EMAIL:String
lateinit var PASSWORD:String
const val NAME = "name"
const val CHARCODE = "charCode"
const val VALUE = "value"
const val PREVIOUS = "previous"
const val NOMINAL = "nominal"

