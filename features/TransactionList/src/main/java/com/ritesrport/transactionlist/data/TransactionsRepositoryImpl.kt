package com.ritesrport.transactionlist.data

import com.ritesrport.core.database.data.dao.TransactionDao
import com.ritesrport.transactionlist.domain.TransactionsRepository
import com.ritesrport.transactionlist.data.mappers.toDomain
import com.ritesrport.transactionlist.domain.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(private val dao: TransactionDao) : TransactionsRepository {
    override val transactions: Flow<List<Transaction>>
        get() = dao.getAll().map { list -> list.mapNotNull { it.toDomain() } }
}