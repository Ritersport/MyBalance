package com.ritesrport.mybalance.domain.repository

import com.ritesrport.mybalance.domain.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: Transaction)
    fun getTransactions(): Flow<List<Transaction>>
    fun getTransactionsForAccount(accountId: Long): Flow<List<Transaction>>
}