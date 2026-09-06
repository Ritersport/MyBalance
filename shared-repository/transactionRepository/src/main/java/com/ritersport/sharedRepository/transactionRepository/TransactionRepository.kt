package com.ritersport.sharedRepository.transactionRepository

import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritersport.sharedRepository.transactionRepository.dto.TransactionDTO
import com.ritesrport.core.database.data.dao.TransactionDao
import com.ritesrport.core.database.data.entity.AccountEntity
import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import com.ritesrport.core.database.data.entity.TransactionEntity
import com.ritesrport.core.database.data.entity.TransactionType
import com.ritesrport.core.database.data.entity.TransactionWithRelations
import com.ritesrport.core.model.IconKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {
    suspend fun addTransaction(transactionDTO: TransactionDTO) = withContext(Dispatchers.IO) {
        transactionDao.insert(transactionDTO.toEntity())
    }

    val transactionsFlow: Flow<List<TransactionDTO>> = transactionDao.getAll().map { list ->
        list.mapNotNull { it.toDTO() }
    }

    private fun TransactionWithRelations.toDTO(): TransactionDTO? {
        return when (this.transaction.type) {
            TransactionType.INCOME -> TransactionDTO.Income(
                transaction.id,
                transaction.title,
                transaction.amount,
                transaction.date,
                sourceCategory?.toDTO() ?: return null,
                destinationAccount?.toDTO() ?: return null,
                transaction.comment
            )

            TransactionType.EXPENSE -> TransactionDTO.Expense(
                transaction.id,
                transaction.title,
                transaction.amount,
                transaction.date,
                sourceAccount?.toDTO() ?: return null,
                destinationCategory?.toDTO() ?: return null,
                transaction.comment
            )

            TransactionType.TRANSFER -> TransactionDTO.Transfer(
                transaction.id,
                transaction.title,
                transaction.amount,
                transaction.date,
                sourceAccount?.toDTO() ?: return null,
                destinationAccount?.toDTO() ?: return null,
                transaction.comment
            )
        }
    }

    private fun IncomeSourceEntity.toDTO(): CategoryDTO {
        return CategoryDTO(id, name, IconKey.getFromValue(icon))
    }

    private fun ExpenseCategoryEntity.toDTO(): CategoryDTO {
        return CategoryDTO(id, name, IconKey.getFromValue(icon))
    }

    private fun AccountEntity.toDTO(): AccountDTO {
        return AccountDTO(id, name, IconKey.getFromValue(icon))
    }

    private fun TransactionDTO.toEntity(): TransactionEntity {
        return when (this) {
            is TransactionDTO.Expense -> this.toEntity()
            is TransactionDTO.Income -> this.toEntity()
            is TransactionDTO.Transfer -> this.toEntity()
        }
    }

    private fun TransactionDTO.Expense.toEntity(): TransactionEntity {
        return TransactionEntity(
            id,
            title,
            TransactionType.EXPENSE,
            amount,
            date,
            sourceAccountId = sourceAccount.id,
            destinationCategoryId = destinationCategory.id,
            comment = comment
        )
    }

    private fun TransactionDTO.Income.toEntity(): TransactionEntity {
        return TransactionEntity(
            id,
            title,
            TransactionType.INCOME,
            amount,
            date,
            sourceCategoryId = sourceCategory.id,
            destinationAccountId = destinationAccount.id,
            comment = comment
        )
    }

    private fun TransactionDTO.Transfer.toEntity(): TransactionEntity {
        return TransactionEntity(
            id,
            title,
            TransactionType.TRANSFER,
            amount,
            date,
            sourceAccountId = sourceAccount.id,
            destinationAccountId = destinationAccount.id,
            comment = comment
        )
    }
}