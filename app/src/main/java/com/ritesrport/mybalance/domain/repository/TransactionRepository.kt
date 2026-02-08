package com.ritesrport.mybalance.domain.repository

import com.ritesrport.mybalance.domain.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: Transaction)
    suspend fun getTransactions(): Flow<List<Transaction>>
}