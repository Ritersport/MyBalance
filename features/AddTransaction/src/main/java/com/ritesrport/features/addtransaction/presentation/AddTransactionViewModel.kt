package com.ritesrport.features.addtransaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesrport.features.addtransaction.domain.AddTransactionInteractor
import com.ritesrport.features.addtransaction.domain.TransactionType
import com.ritesrport.features.addtransaction.presentation.model.AddTransactionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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
}

sealed interface AddTransactionUiState {
    data object Loading : AddTransactionUiState
    data class Error(val throwable: Throwable) : AddTransactionUiState
    data class Success(val state: AddTransactionState) : AddTransactionUiState
}
