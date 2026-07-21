package com.ritesrport.transactionlist.di

import com.ritesrport.core.database.data.dao.TransactionDao
import com.ritesrport.transactionlist.data.TransactionsRepositoryImpl
import com.ritesrport.transactionlist.domain.TransactionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TransactionListModule {
    @Provides
    fun provideTransactionsRepository(dao: TransactionDao): TransactionsRepository {
        return TransactionsRepositoryImpl(dao)
    }
}