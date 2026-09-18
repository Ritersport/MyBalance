package com.ritesrport.features.addtransaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesrport.features.addtransaction.domain.AddTransactionInteractor
import com.ritesrport.features.addtransaction.domain.TransactionType
import com.ritesrport.features.addtransaction.presentation.model.AddTransactionState
import com.ritersport.sharedRepository.transactionRepository.dto.TransactionDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionInteractor: AddTransactionInteractor,
) : ViewModel() {

    private val _type = MutableStateFlow(TransactionType.EXPENSE)
    private val _amount = MutableStateFlow("0")
    private val _date = MutableStateFlow(LocalDate.now())
    private val _note = MutableStateFlow<String?>(null)

    private val _navigationEvent = MutableSharedFlow<AddTransactionNavigationEvent>()
    val navigationEvent: SharedFlow<AddTransactionNavigationEvent> = _navigationEvent.asSharedFlow()

    val state: StateFlow<AddTransactionUiState> = combine(
        _type.flatMapLatest { type ->
            transactionInteractor.getDefaultDataFlow(type).map { type to it }
        },
        _amount,
        _date,
        _note
    ) { (type, data), amount, date, note ->
        val (category, account) = data
        AddTransactionUiState.Success(
            AddTransactionState.build(
                type = type,
                category = category,
                account = account,
                amount = amount,
                date = date,
                note = note
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AddTransactionUiState.Loading
    )

    fun onNumberClick(key: String) {
        _amount.update { current ->
            when (key) {
                "⌫" -> if (current.length > 1) current.dropLast(1) else "0"
                "," -> if (current.contains(",")) current else "$current,"
                else -> if (current == "0") key else current + key
            }
        }
    }

    fun onTypeChanged(type: TransactionType) {
        _type.value = type
    }

    fun onNoteChanged(note: String) {
        _note.value = note.takeIf { it.isNotEmpty() }
    }

    fun onSaveClick() {
        val uiState = state.value
        if (uiState is AddTransactionUiState.Success) {
            viewModelScope.launch {
                val state = uiState.state
                val amount = state.amount.replace(",", ".").toBigDecimalOrNull() ?: BigDecimal.ZERO
                val transactionDTO = when (state) {
                    is AddTransactionState.Expense -> TransactionDTO.Expense(
                        amount = amount,
                        date = state.date,
                        sourceAccount = state.sourceAccount,
                        destinationCategory = state.destinationCategory,
                        comment = state.note
                    )

                    is AddTransactionState.Income -> TransactionDTO.Income(
                        amount = amount,
                        date = state.date,
                        sourceCategory = state.sourceCategory,
                        destinationAccount = state.destinationAccount,
                        comment = state.note
                    )

                    is AddTransactionState.Transfer -> TransactionDTO.Transfer(
                        amount = amount,
                        date = state.date,
                        sourceAccount = state.sourceAccount,
                        destinationAccount = state.destinationAccount,
                        comment = state.note
                    )
                }
                transactionInteractor.addTransaction(transactionDTO)
                _navigationEvent.emit(AddTransactionNavigationEvent.Back)
            }
        }
    }
}

sealed interface AddTransactionNavigationEvent {
    data object Back : AddTransactionNavigationEvent
}

sealed interface AddTransactionUiState {
    data object Loading : AddTransactionUiState
    data class Error(val throwable: Throwable) : AddTransactionUiState
    data class Success(val state: AddTransactionState) : AddTransactionUiState
}
