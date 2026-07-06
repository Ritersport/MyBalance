package com.ritesrport.core.database.data.repository

import com.ritesrport.core.database.data.dao.TransactionDao
import com.ritesrport.core.database.data.entity.mappers.toDomain
import com.ritesrport.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalTransactionsRepository(private val dao: TransactionDao) : TransactionsRepository {
    override val transactions: Flow<List<Transaction>>
        get() = dao.getAll().map { list -> list.map { it.toDomain() }.filterNotNull() }
}