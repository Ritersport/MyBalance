package com.ritesrport.features.addtransaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesrport.features.addtransaction.domain.AddTransactionInteractor
import com.ritesrport.features.addtransaction.domain.TransactionType
import com.ritesrport.features.addtransaction.presentation.model.AddTransactionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionInteractor: AddTransactionInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow<AddTransactionUiState>(
        AddTransactionUiState.Loading
    )
    val state: StateFlow<AddTransactionUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val defaultCategory = transactionInteractor.getDefaultCategory()
            val account = transactionInteractor.getDefaultAccount()
            _state.update {
                AddTransactionUiState.Success(
                    AddTransactionState.buildDefault(
                        defaultCategory,
                        account
                    )
                )
            }

        }
    }

    fun onNumberClick(key: String) {
        updateSuccessState { current ->
            val newAmount = when (key) {
                "⌫" -> if (current.amount.length > 1) current.amount.dropLast(1) else "0"
                "," -> if (current.amount.contains(",")) current.amount else current.amount + ","
                else -> if (current.amount == "0") key else current.amount + key
            }
            current.copyState(amount = newAmount)
        }
    }

    fun onTypeChanged(type: TransactionType) {
        viewModelScope.launch {
            val category = transactionInteractor.getDefaultCategory()
            val account = transactionInteractor.getDefaultAccount()
            updateSuccessState { current ->
                AddTransactionState.build(
                    type = type,
                    category = category,
                    account = account,
                    amount = current.amount,
                    date = current.date,
                    note = current.note
                )
            }
        }
    }

    private fun updateSuccessState(transform: (AddTransactionState) -> AddTransactionState) {
        _state.update { currentState ->
            (currentState as? AddTransactionUiState.Success)?.let {
                it.copy(state = transform(it.state))
            } ?: currentState
        }
    }
}

sealed interface AddTransactionUiState {
    data object Loading : AddTransactionUiState
    data class Error(val throwable: Throwable) : AddTransactionUiState
    data class Success(val state: AddTransactionState) : AddTransactionUiState
}
