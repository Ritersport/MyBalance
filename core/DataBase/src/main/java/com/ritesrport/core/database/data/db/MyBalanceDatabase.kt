package com.ritesrport.core.database.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ritesrport.core.database.data.dao.TransactionDao
import com.ritesrport.core.database.data.entity.AccountEntity
import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import com.ritesrport.core.database.data.entity.converters.BigDecimalConverter
import com.ritesrport.core.database.data.entity.converters.LocalDateConverter
import com.ritesrport.core.database.data.entity.converters.TransactionTypeConverter
import com.ritesrport.core.database.data.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class, AccountEntity::class, ExpenseCategoryEntity::class, IncomeSourceEntity::class],
    version = 1
)
@TypeConverters(
    BigDecimalConverter::class,
    LocalDateConverter::class,
    TransactionTypeConverter::class
)
abstract class MyBalanceDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}