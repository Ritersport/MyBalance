package com.ritesrport.features.addtransaction.domain

import com.ritersport.sharedRepository.transactionRepository.TransactionRepository
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritersport.sharedRepository.transactionRepository.dto.TransactionDTO
import com.ritesrport.core.model.IconKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddTransactionInteractor @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend fun addTransaction(transactionDTO: TransactionDTO) {
        transactionRepository.addTransaction(transactionDTO)
    }

    suspend fun getDefaultCategory(): CategoryDTO = withContext(Dispatchers.IO) {
        CategoryDTO(0, "Еда и напитки", IconKey.Bus)
    }

    suspend fun getDefaultAccount(): AccountDTO = withContext(Dispatchers.IO) {
        AccountDTO(0, "Счёт по умолчанию", IconKey.Bus)
    }

}
