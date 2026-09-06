package com.ritesrport.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM ExpenseCategoryEntity")
    fun getAllExpenseCategories(): Flow<List<ExpenseCategoryEntity>>

    @Query("SELECT * FROM IncomeSourceEntity")
    fun getAllIncomeSources(): Flow<List<IncomeSourceEntity>>

    @Insert
    suspend fun insertExpenseCategory(item: ExpenseCategoryEntity)

    @Insert
    suspend fun insertIncomeSource(item: IncomeSourceEntity)
}
