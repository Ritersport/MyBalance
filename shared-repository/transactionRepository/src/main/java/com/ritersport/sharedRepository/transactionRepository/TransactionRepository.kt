package com.ritersport.sharedRepository.transactionRepository

import com.ritersport.sharedRepository.transactionRepository.dto.TransactionDTO
import com.ritesrport.core.database.data.dao.TransactionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionRepository @Inject constructor(transactionDao: TransactionDao) {
    suspend fun addTransaction(transactionDTO: TransactionDTO) = withContext(Dispatchers.IO) {
        //transactionDao.insert()
    }

    val transactionsFlow = transactionDao.getAll()
}