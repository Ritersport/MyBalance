package com.ritesrport.core.database.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ritesrport.core.database.data.dao.AccountDao
import com.ritesrport.core.database.data.dao.CategoryDao
import com.ritesrport.core.database.data.dao.TransactionDao
import com.ritesrport.core.database.data.entity.AccountEntity
import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import com.ritesrport.core.database.data.entity.converters.BigDecimalConverter
import com.ritesrport.core.database.data.entity.converters.LocalDateConverter
import com.ritesrport.core.database.data.entity.converters.TransactionTypeConverter
import com.ritesrport.core.database.data.entity.TransactionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Provider

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

    abstract fun accountDao(): AccountDao

    abstract fun categoryDao(): CategoryDao

    companion object {
        fun getCallback(
            categoryDaoProvider: Provider<CategoryDao>,
            defaultCategoriesProvider: Provider<DefaultCategoriesProvider>
        ): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        val categoryDao = categoryDaoProvider.get()
                        val defaults = defaultCategoriesProvider.get()
                        defaults.expenseCategories.forEach {
                            categoryDao.insertExpenseCategory(it)
                        }
                        defaults.incomeSources.forEach {
                            categoryDao.insertIncomeSource(it)
                        }
                    }
                }
            }
        }
    }
}