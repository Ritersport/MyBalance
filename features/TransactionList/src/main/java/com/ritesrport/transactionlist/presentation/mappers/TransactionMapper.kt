package com.ritesrport.transactionlist.presentation.mappers

import androidx.compose.ui.graphics.Color
import com.ritesrport.items.transactioncard.TransactionModel
import com.ritesrport.items.transactioncard.TransactionType
import com.ritesrport.transactionlist.domain.Transaction

fun Transaction.toPresentation(): TransactionModel {
    return when(this) {
        is Transaction.Expense -> toPresentation()
        is Transaction.Income -> toPresentation()
        is Transaction.Transfer -> toPresentation()
    }
}

private fun Transaction.Income.toPresentation(): TransactionModel {
    return TransactionModel(
        id,
        comment ?: source.name,
        source.name,
        amount.toPlainString(), //TODO: DesignSystem
        TransactionType.INCOME,
        account.name,
        Icons.from(source.icon),
        Color(12, 24, 136, 255),
        date
    )
}

private fun Transaction.Expense.toPresentation(): TransactionModel {
    return TransactionModel(
        id,
        comment ?: category.name,
        category.name,
        amount.toPlainString(), //TODO: DesignSystem
        TransactionType.EXPENSE,
        account.name,
        Icons.from(category.icon),
        Color(12, 24, 136, 255),
        date
    )
}

private fun Transaction.Transfer.toPresentation(): TransactionModel {
    return TransactionModel(
        id,
        comment ?: sourceAccount.name,
        sourceAccount.name,
        amount.toPlainString(), //TODO: DesignSystem
        TransactionType.EXPENSE,
        destinationAccount.name,
        Icons.from(sourceAccount.icon),
        Color(12, 24, 136, 255),
        date
    )
}