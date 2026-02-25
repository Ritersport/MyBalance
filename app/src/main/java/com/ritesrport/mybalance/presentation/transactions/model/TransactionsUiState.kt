package com.ritesrport.mybalance.presentation.transactions.model

sealed interface TransactionsUiState {
    data class Content(val items: List<TransactionItemUi>) : TransactionsUiState
    data class Error(val message: String) : TransactionsUiState
    object Loading : TransactionsUiState
}