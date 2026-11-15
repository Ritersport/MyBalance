package com.ritesrport.features.addtransaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ritesrport.features.addtransaction.domain.AddTransactionInteractor
import com.ritesrport.features.addtransaction.domain.TransactionType
import com.ritesrport.features.addtransaction.presentation.model.AddTransactionState
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
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

    private val _selectedExpenseCategory = MutableStateFlow<CategoryDTO?>(null)
    private val _selectedIncomeCategory = MutableStateFlow<CategoryDTO?>(null)
    private val _selectedSourceAccount = MutableStateFlow<AccountDTO?>(null)
    private val _selectedDestinationAccount = MutableStateFlow<AccountDTO?>(null)

    val expenseCategories: StateFlow<List<CategoryDTO>> = transactionInteractor.getExpenseCategories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val incomeCategories: StateFlow<List<CategoryDTO>> = transactionInteractor.getIncomeSources()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val accounts: StateFlow<List<AccountDTO>> = transactionInteractor.getAccounts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _navigationEvent = MutableSharedFlow<AddTransactionNavigationEvent>()
    val navigationEvent: SharedFlow<AddTransactionNavigationEvent> = _navigationEvent.asSharedFlow()

    val state: StateFlow<AddTransactionUiState> = combine(
        combine(
            _type,
            _amount,
            _date,
            _note
        ) { type, amount, date, note ->
            CommonFormFields(type, amount, date, note)
        },
        _type.flatMapLatest { type ->
            transactionInteractor.getDefaultDataFlow(type).map { type to it }
        },
        combine(
            _selectedExpenseCategory,
            _selectedIncomeCategory,
            _selectedSourceAccount,
            _selectedDestinationAccount
        ) { expCat, incCat, srcAcc, dstAcc ->
            UserSelections(expCat, incCat, srcAcc, dstAcc)
        }
    ) { common, (_, defaultData), selections ->
        val (defaultCategory, defaultAccount) = defaultData
        val finalExpenseCategory = selections.expenseCategory ?: defaultCategory
        val finalIncomeCategory = selections.incomeCategory ?: defaultCategory
        val finalSourceAccount = selections.sourceAccount ?: defaultAccount
        val finalDestinationAccount = selections.destinationAccount ?: defaultAccount

        val transactionState = when (common.type) {
            TransactionType.EXPENSE -> AddTransactionState.Expense(
                destinationCategory = finalExpenseCategory,
                sourceAccount = finalSourceAccount,
                tags = emptyList(),
                amount = common.amount,
                date = common.date,
                note = common.note
            )

            TransactionType.INCOME -> AddTransactionState.Income(
                sourceCategory = finalIncomeCategory,
                destinationAccount = finalDestinationAccount,
                tags = emptyList(),
                amount = common.amount,
                date = common.date,
                note = common.note
            )

            TransactionType.TRANSFER -> AddTransactionState.Transfer(
                sourceAccount = finalSourceAccount,
                destinationAccount = finalDestinationAccount,
                tags = emptyList(),
                amount = common.amount,
                date = common.date,
                note = common.note
            )
        }

        AddTransactionUiState.Success(transactionState)
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

    fun onExpenseCategoryClick() {
        viewModelScope.launch {
            _navigationEvent.emit(AddTransactionNavigationEvent.SelectExpenseCategory)
        }
    }

    fun onIncomeCategoryClick() {
        viewModelScope.launch {
            _navigationEvent.emit(AddTransactionNavigationEvent.SelectIncomeCategory)
        }
    }

    fun onSourceAccountClick() {
        viewModelScope.launch {
            _navigationEvent.emit(AddTransactionNavigationEvent.SelectSourceAccount)
        }
    }

    fun onDestinationAccountClick() {
        viewModelScope.launch {
            _navigationEvent.emit(AddTransactionNavigationEvent.SelectDestinationAccount)
        }
    }

    fun onExpenseCategorySelected(category: CategoryDTO) {
        _selectedExpenseCategory.value = category
    }

    fun onIncomeCategorySelected(category: CategoryDTO) {
        _selectedIncomeCategory.value = category
    }

    fun onSourceAccountSelected(account: AccountDTO) {
        _selectedSourceAccount.value = account
    }

    fun onDestinationAccountSelected(account: AccountDTO) {
        _selectedDestinationAccount.value = account
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

    private data class CommonFormFields(
        val type: TransactionType,
        val amount: String,
        val date: LocalDate,
        val note: String?
    )

    private data class UserSelections(
        val expenseCategory: CategoryDTO?,
        val incomeCategory: CategoryDTO?,
        val sourceAccount: AccountDTO?,
        val destinationAccount: AccountDTO?
    )
}

sealed interface AddTransactionNavigationEvent {
    data object Back : AddTransactionNavigationEvent
    data object SelectExpenseCategory : AddTransactionNavigationEvent
    data object SelectIncomeCategory : AddTransactionNavigationEvent
    data object SelectSourceAccount : AddTransactionNavigationEvent
    data object SelectDestinationAccount : AddTransactionNavigationEvent
}

sealed interface AddTransactionUiState {
    data object Loading : AddTransactionUiState
    data class Error(val throwable: Throwable) : AddTransactionUiState
    data class Success(val state: AddTransactionState) : AddTransactionUiState
}
