package com.ritesrport.transactionlist.data.mappers

import com.ritesrport.core.database.data.entity.AccountEntity
import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import com.ritesrport.core.database.data.entity.TransactionType
import com.ritesrport.core.database.data.entity.TransactionWithRelations
import com.ritesrport.transactionlist.domain.Transaction

fun TransactionWithRelations.toDomain(): Transaction? {
    return when (transaction.type) {
        TransactionType.INCOME -> toIncomeDomain(sourceCategory, destinationAccount)
        TransactionType.EXPENSE -> toExpenseDomain(destinationCategory, sourceAccount)
        TransactionType.TRANSFER -> toTransferDomain(sourceAccount, destinationAccount)
    }
}

private fun TransactionWithRelations.toIncomeDomain(
    incomeSourceEntity: IncomeSourceEntity?, accountEntity: AccountEntity?
): Transaction? {
    val category = incomeSourceEntity?.toDomain() ?: return null
    val account = accountEntity?.toDomain() ?: return null
    return Transaction.Income(
        transaction.id,
        transaction.amount,
        transaction.date,
        category,
        account,
        transaction.comment
    )
}

private fun TransactionWithRelations.toExpenseDomain(
    categoryEntity: ExpenseCategoryEntity?,
    accountEntity: AccountEntity?
): Transaction? {
    val category = categoryEntity?.toDomain() ?: return null
    val account = accountEntity?.toDomain() ?: return null
    return Transaction.Expense(
        transaction.id,
        transaction.amount,
        transaction.date,
        category,
        account,
        transaction.comment
    )
}

private fun TransactionWithRelations.toTransferDomain(
    sourceAccountEntity: AccountEntity?,
    destinationAccountEntity: AccountEntity?
): Transaction? {
    val sourceAccount = sourceAccountEntity?.toDomain() ?: return null
    val destinationAccount = destinationAccountEntity?.toDomain() ?: return null
    return Transaction.Transfer(
        transaction.id,
        transaction.amount,
        transaction.date,
        sourceAccount,
        destinationAccount,
        transaction.comment
    )
}