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


import com.ritesrport.items.transactioncard.TransactionType
import com.ritesrport.items.transactioncard.transactionModelExpensePreview
import com.ritesrport.items.transactioncard.transactionModelIncomePreview
import java.time.LocalDate

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : ViewModel(), TransactionInterface {

    private val _state: MutableStateFlow<TransactionListUiState> =
        MutableStateFlow(TransactionListUiState.Loading)
    val state = _state

    private var allTransactions: List<TransactionModel> = emptyList()
    private var currentFilter: TransactionTypeFilter = TransactionTypeFilter.ALL

    init {
        viewModelScope.launch(Dispatchers.IO) {
            transactionsRepository.transactions.collect { transactions ->
                allTransactions = transactions.map { it.toPresentation() }
                updateState()
            }
        }
    }

    fun onFilterChanged(filter: TransactionTypeFilter) {
        currentFilter = filter
        updateState()
    }

    private fun updateState() {
        val filtered = if (currentFilter == TransactionTypeFilter.ALL) {
            allTransactions
        } else {
            allTransactions.filter {
                when (currentFilter) {
                    TransactionTypeFilter.INCOME -> it.type == TransactionType.INCOME
                    TransactionTypeFilter.EXPENSE -> it.type == TransactionType.EXPENSE
                    TransactionTypeFilter.TRANSFER -> it.type == TransactionType.TRANSFER
                    else -> true
                }
            }
        }

        val groups = filtered.groupBy { it.date }
            .map { (date, items) -> TransactionGroup(date, items) }
            .sortedByDescending { it.date }

        _state.value = TransactionListUiState.Success(groups, currentFilter)
    }

    override fun onTransactionCardClick(transactionId: Long) {

    }
}

sealed interface TransactionListUiState {
    object Loading : TransactionListUiState
    object Empty : TransactionListUiState
    data class Error(val throwable: Throwable) : TransactionListUiState
    data class Success(
        val groups: List<TransactionGroup>,
        val currentFilter: TransactionTypeFilter
    ) : TransactionListUiState
}

val transactionListPreview = TransactionListUiState.Success(
    listOf(
        TransactionGroup(LocalDate.now(), listOf(transactionModelIncomePreview)),
        TransactionGroup
            (
            LocalDate.now().minusDays(1), listOf(
            transactionModelIncomePreview,
            transactionModelExpensePreview
        )
        )
    ), TransactionTypeFilter.ALL
)

data class TransactionGroup(
    val date: LocalDate,
    val items: List<TransactionModel>
)

enum class TransactionTypeFilter {
    ALL, EXPENSE, INCOME, TRANSFER
}