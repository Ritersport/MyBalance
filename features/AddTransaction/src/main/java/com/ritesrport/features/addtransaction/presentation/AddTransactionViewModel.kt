package com.ritesrport.features.addtransaction.presentation

import androidx.lifecycle.ViewModel
import com.ritesrport.features.addtransaction.presentation.domain.AddTransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val addTransactionRepository: AddTransactionRepository
) : ViewModel() {
    val state: StateFlow<AddTransactionUiState>
        get() = _state

    private val _state: MutableStateFlow<AddTransactionUiState> =
        MutableStateFlow(AddTransactionUiState.Loading)
}

sealed interface AddTransactionUiState {
    object Loading : AddTransactionUiState
    data class Error(val throwable: Throwable) : AddTransactionUiState
    data class Success(val state: AddTransactionState) : AddTransactionUiState
}