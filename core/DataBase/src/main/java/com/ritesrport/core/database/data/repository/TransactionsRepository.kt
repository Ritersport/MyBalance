package com.ritesrport.core.database.data.repository

import com.ritesrport.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    val transactions: Flow<List<Transaction>>
}