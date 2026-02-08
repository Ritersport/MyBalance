package com.ritesrport.mybalance.domain.use_case

import com.ritesrport.mybalance.domain.repository.AccountRepository
import com.ritesrport.mybalance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.math.BigDecimal

class GetBalanceUseCase(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(accountId: Long): Flow<BigDecimal> {
        return combine(
            accountRepository.getAccount(accountId),
            transactionRepository.getTransactionsForAccount(accountId)
        ) { account, transactions ->
              account.initialBalance + transactions.sumOf { it.amount }
        }
    }
}