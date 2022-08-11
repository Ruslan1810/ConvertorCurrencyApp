package ru.ruslan.convertorcurrencyapp.domain.models.modelDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "currencies_table")
data class CurrencyDB (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var charCode: String = "",
    @ColumnInfo
    var name: String = "",
    @ColumnInfo
    var nominal: String = "",
    @ColumnInfo
    var value: String = "",
    @ColumnInfo
    var previous: String = "",

    val idFirebase: String = ""
): Serializable