package com.ritesrport.features.addtransaction.domain

import com.ritersport.sharedRepository.transactionRepository.TransactionRepository
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritersport.sharedRepository.transactionRepository.dto.TransactionDTO
import com.ritesrport.core.database.data.dao.AccountDao
import com.ritesrport.core.database.data.dao.CategoryDao
import com.ritesrport.core.database.data.entity.AccountEntity
import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import com.ritesrport.core.model.IconKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.math.BigDecimal
import javax.inject.Inject

class AddTransactionInteractor @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountDao: AccountDao,
    private val categoryDao: CategoryDao
) {
    suspend fun addTransaction(transactionDTO: TransactionDTO) {
        transactionRepository.addTransaction(transactionDTO)
    }

    fun getDefaultDataFlow(type: TransactionType): Flow<Pair<CategoryDTO, AccountDTO>> {
        val categoryFlow = when (type) {
            TransactionType.EXPENSE -> categoryDao.getAllExpenseCategories().map { list ->
                list.firstOrNull()?.toDTO() ?: CategoryDTO(0, "Еда", IconKey.Bus)
            }
            TransactionType.INCOME -> categoryDao.getAllIncomeSources().map { list ->
                list.firstOrNull()?.toDTO() ?: CategoryDTO(0, "Зарплата", IconKey.Bus)
            }
            TransactionType.TRANSFER -> flowOf(CategoryDTO(0, "Перевод", IconKey.Bus))
        }

        val accountFlow = accountDao.getAll()
            .onEach { list ->
                if (list.isEmpty()) {
                    accountDao.insert(AccountEntity(0, "Основной счет", BigDecimal.ZERO, IconKey.Bus.value))
                }
            }
            .filter { it.isNotEmpty() }
            .map { it.first().toDTO() }

        return combine(categoryFlow, accountFlow) { category, account ->
            category to account
        }
    }

    private fun AccountEntity.toDTO() = AccountDTO(id, name, IconKey.getFromValue(icon), balance?.toDouble() ?: 0.0)
    private fun ExpenseCategoryEntity.toDTO() = CategoryDTO(id, name, IconKey.getFromValue(icon))
    private fun IncomeSourceEntity.toDTO() = CategoryDTO(id, name, IconKey.getFromValue(icon))
}
