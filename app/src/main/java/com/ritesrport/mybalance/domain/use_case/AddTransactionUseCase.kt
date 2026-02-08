package com.ritesrport.mybalance.domain.use_case

import com.ritesrport.mybalance.domain.repository.TransactionRepository
import com.ritesrport.mybalance.domain.entity.Transaction

class AddTransactionUseCase(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction) {
        repository.addTransaction(transaction)
    }
}