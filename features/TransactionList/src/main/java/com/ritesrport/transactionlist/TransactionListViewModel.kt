package com.ritesrport.transactionlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesrport.core.data.TransactionsRepository
import com.ritesrport.items.transactioncard.TransactionInterface
import com.ritesrport.items.transactioncard.TransactionModel
import com.ritesrport.items.transactioncard.transactionModelPreview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
        viewModelScope.launch {
            delay(3000)
            _state.value = TransactionListUiState.Success(listOf(transactionModelPreview))
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