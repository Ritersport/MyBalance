package com.ritesrport.transactionlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesrport.items.transactioncard.TransactionInterface
import com.ritesrport.items.transactioncard.TransactionModel
import com.ritesrport.transactionlist.domain.TransactionsRepository
import com.ritesrport.transactionlist.presentation.mappers.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : ViewModel(), TransactionInterface {

    val state: StateFlow<TransactionListUiState>
        get() = _state

    private val _state: MutableStateFlow<TransactionListUiState> =
        MutableStateFlow(TransactionListUiState.Loading)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            transactionsRepository.transactions.collect { transactions ->
                _state.value =
                    TransactionListUiState.Success(transactions.map { it.toPresentation() })
            }
        }
    }

    override fun onTransactionCardClick(transactionId: Long) {

    }
}

sealed interface TransactionListUiState {
    object Loading : TransactionListUiState
    data class Error(val throwable: Throwable) : TransactionListUiState
    data class Success(val items: List<TransactionModel>) : TransactionListUiState
}