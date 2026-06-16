package com.ritesrport.core.database.data.entity.converters

import androidx.room.TypeConverter
import com.ritesrport.core.database.data.entity.TransactionType

class TransactionTypeConverter {

    @TypeConverter
    fun fromType(type: TransactionType): String {
        return type.name
    }

    @TypeConverter
    fun toType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }
}