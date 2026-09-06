package com.ritesrport.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ritesrport.core.database.data.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM AccountEntity")
    fun getAll(): Flow<List<AccountEntity>>

    @Insert
    suspend fun insert(item: AccountEntity)
}
