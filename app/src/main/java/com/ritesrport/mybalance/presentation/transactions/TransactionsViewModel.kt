package com.ritesrport.mybalance.presentation.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesrport.mybalance.domain.use_case.GetTransactionsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TransactionsViewModel(
    getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {
    val state: StateFlow<List<TransactionsUiState>> = getTransactionsUseCase()
        .map { transactions ->
            transactions.map { transaction ->
                TransactionsUiState()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}