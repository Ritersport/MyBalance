package com.ritesrport.features.addtransaction.domain

import com.ritersport.sharedRepository.transactionRepository.TransactionRepository
import com.ritersport.sharedRepository.transactionRepository.dto.TransactionDTO
import javax.inject.Inject

class AddTransactionInteractor @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun addTransaction(transactionDTO: TransactionDTO) {
        transactionRepository.addTransaction(transactionDTO)
    }

    val transactionFlow = transactionRepository.transactionsFlow //todo add mapping to shared repo
}