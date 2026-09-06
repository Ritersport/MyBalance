package com.ritesrport.features.addtransaction.presentation

import androidx.lifecycle.ViewModel
import com.ritesrport.features.addtransaction.domain.AddTransactionInteractor
import com.ritesrport.features.addtransaction.domain.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionInteractor: AddTransactionInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<AddTransactionUiState>(
        AddTransactionUiState.Success(AddTransactionState.default)
    )
    val state: StateFlow<AddTransactionUiState> = _state.asStateFlow()

    fun onNumberClick(key: String) {
        updateSuccessState { current ->
            val newAmount = when (key) {
                "⌫" -> if (current.amount.length > 1) current.amount.dropLast(1) else "0"
                "," -> if (current.amount.contains(",")) current.amount else current.amount + ","
                else -> if (current.amount == "0") key else current.amount + key
            }
            current.copy(amount = newAmount)
        }
    }

    fun onTypeChanged(type: TransactionType) {
        updateSuccessState { it.copy(transactionType = type) }
    }

    private fun updateSuccessState(transform: (AddTransactionState) -> AddTransactionState) {
        _state.update { currentState ->
            if (currentState is AddTransactionUiState.Success) {
                currentState.copy(state = transform(currentState.state))
            } else {
                currentState
            }
        }
    }
}

sealed interface AddTransactionUiState {
    data object Loading : AddTransactionUiState
    data class Error(val throwable: Throwable) : AddTransactionUiState
    data class Success(val state: AddTransactionState) : AddTransactionUiState
}
