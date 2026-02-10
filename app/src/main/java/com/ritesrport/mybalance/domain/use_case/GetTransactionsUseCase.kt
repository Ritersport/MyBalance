package com.ritesrport.mybalance.domain.use_case

import com.ritesrport.mybalance.domain.repository.TransactionRepository
import com.ritesrport.mybalance.domain.entity.Transaction
import kotlinx.coroutines.flow.Flow

class GetTransactionsUseCase(private val repository: TransactionRepository) {
    operator fun invoke(): Flow<List<Transaction>> {
        return repository.getTransactions()
    }
}