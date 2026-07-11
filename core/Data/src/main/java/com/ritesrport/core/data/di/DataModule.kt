package com.ritesrport.core.data.di

import com.ritesrport.core.data.LocalTransactionsRepository
import com.ritesrport.core.data.TransactionsRepository
import com.ritesrport.model.Transaction
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsTransactionsRepository(
        myModelRepository: LocalTransactionsRepository
    ): TransactionsRepository
}

class FakeTransactionsRepository @Inject constructor() : TransactionsRepository {
    override val transactions: Flow<List<Transaction>>
        get() = TODO("Not yet implemented")


}

