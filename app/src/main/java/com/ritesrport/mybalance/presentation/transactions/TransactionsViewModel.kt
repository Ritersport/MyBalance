package com.ritesrport.mybalance.presentation.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesrport.mybalance.domain.use_case.GetTransactionsUseCase
import com.ritesrport.mybalance.presentation.transactions.model.TransactionsUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TransactionsViewModel(
    getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {
    val state: StateFlow<TransactionsUiState> =
        getTransactionsUseCase().map { it.toUiState() }.catch { throwable ->
                emit(TransactionsUiState.Error(throwable.message ?: "Unknown error"))
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = TransactionsUiState.Loading
            )
}