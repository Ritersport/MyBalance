package com.ritesrport.mybalance.presentation.transactions

import com.ritesrport.mybalance.domain.entity.Expense
import com.ritesrport.mybalance.domain.entity.Income
import com.ritesrport.mybalance.domain.entity.Transaction
import com.ritesrport.mybalance.domain.entity.Transfer
import com.ritesrport.mybalance.presentation.transactions.model.TransactionItemUi
import com.ritesrport.mybalance.presentation.transactions.model.TransactionsUiState
import com.ritesrport.mybalance.presentation.transactions.model.toCategoryItem

fun Transaction.toUiState(): TransactionItemUi {
    when (this) {
        is Expense -> {
            val account = account.toCategoryItem()
            val category = category.toCategoryItem()
            return TransactionItemUi(id, amount, account, category, comment)
        }

        is Income -> {
            val source = source.toCategoryItem()
            val destination = account.toCategoryItem()
            return TransactionItemUi(id, amount, source, destination, comment)
        }

        is Transfer -> {
            val source = sourceAccount.toCategoryItem()
            val destination = destinationAccount.toCategoryItem()
            return TransactionItemUi(id, amount, source, destination, comment)
        }
    }
}

fun List<Transaction>.toUiState(): TransactionsUiState =
    TransactionsUiState.Content(map(Transaction::toUiState))