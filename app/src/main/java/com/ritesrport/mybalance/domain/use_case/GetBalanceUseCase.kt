package com.ritesrport.mybalance.domain.use_case

import com.ritesrport.mybalance.domain.entity.Balance
import com.ritesrport.mybalance.domain.repository.AccountRepository
import com.ritesrport.mybalance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetBalanceUseCase(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): Flow<Balance> {
        return combine(
            accountRepository.getAccount(),
            transactionRepository.getTransactions()
        ) { account, transactions ->
            Balance(
                account.initialBalance + transactions.sumOf { it.amount }
            )
        }
    }
}