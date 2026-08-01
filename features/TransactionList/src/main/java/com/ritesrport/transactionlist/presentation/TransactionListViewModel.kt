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
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : ViewModel(), TransactionInterface {

    var state: MutableStateFlow<TransactionListUiState> = MutableStateFlow(TransactionListUiState.Loading)
        private set


    init {
        viewModelScope.launch(Dispatchers.IO) {
            transactionsRepository.transactions.collect { transactions ->
                state.value =
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