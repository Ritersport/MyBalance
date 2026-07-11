package com.ritesrport.core.data

import com.ritesrport.core.database.data.dao.TransactionDao
import com.ritesrport.core.database.data.entity.mappers.toDomain
import com.ritesrport.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface TransactionsRepository {
    val transactions: Flow<List<Transaction>>
}

class LocalTransactionsRepository @Inject constructor(private val dao: TransactionDao) : TransactionsRepository {
    override val transactions: Flow<List<Transaction>>
        get() = dao.getAll().map { list -> list.mapNotNull { it.toDomain() } }
}