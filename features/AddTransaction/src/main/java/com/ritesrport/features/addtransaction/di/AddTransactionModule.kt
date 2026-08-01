package com.ritesrport.features.addtransaction.di

import com.ritesrport.features.addtransaction.data.AddTransactionRepositoryImpl
import com.ritesrport.features.addtransaction.presentation.domain.AddTransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AddTransactionModule {
    @Provides
    fun provideTransactionsRepository(): AddTransactionRepository {
        return AddTransactionRepositoryImpl()
    }
}