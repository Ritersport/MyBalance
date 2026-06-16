package com.ritesrport.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ritesrport.core.database.data.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionEntity")
    suspend fun getAll(): List<TransactionEntity>

    @Insert
    suspend fun insert(item: TransactionEntity)
}