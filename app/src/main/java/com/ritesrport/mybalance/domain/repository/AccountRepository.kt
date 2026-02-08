package com.ritesrport.mybalance.domain.repository

import com.ritesrport.mybalance.domain.entity.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getAccount(): Flow<Account>
}