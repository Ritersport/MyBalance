package com.ritesrport.transactionlist.domain

import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    val transactions: Flow<List<Transaction>>
}