package com.ritesrport.transactionlist.domain

import com.ritersport.sharedRepository.transactionRepository.TransactionRepository
import javax.inject.Inject

class TransactionListInteractor@Inject constructor(
    transactionRepository: TransactionRepository
) {
    val transactionFlow = transactionRepository.transactionsFlow
}