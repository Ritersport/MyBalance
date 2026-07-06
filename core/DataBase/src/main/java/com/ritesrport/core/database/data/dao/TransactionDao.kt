package com.ritesrport.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ritesrport.core.database.data.entity.TransactionEntity
import com.ritesrport.core.database.data.entity.TransactionWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionEntity")
    fun getAll(): Flow<List<TransactionWithRelations>>

    @Insert
    suspend fun insert(item: TransactionEntity)
}