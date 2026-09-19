package com.ritesrport.features.addtransaction.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritesrport.core.designsystem.theme.MyBalanceTheme
import com.ritesrport.features.addtransaction.domain.TransactionType
import com.ritesrport.features.addtransaction.presentation.composables.AddTransactionToolbar
import com.ritesrport.features.addtransaction.presentation.composables.NumberKeyboard
import com.ritesrport.features.addtransaction.presentation.composables.TagChip
import com.ritesrport.features.addtransaction.presentation.composables.TransactionTypeSelector
import com.ritesrport.features.addtransaction.presentation.model.AddTransactionState
import com.ritesrport.features.addtransaction.presentation.model.addTransactionStatePreview
import com.ritesrport.features.addtransaction.presentation.model.toSettingsItem
import com.ritesrport.items.settingsitem.SettingsRow
import com.ritesrport.items.settingsitem.settingsInterfacePreview

@Composable
fun AddTransactionScreen(
    onBack: () -> Unit,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                AddTransactionNavigationEvent.Back -> onBack()
            }
        }
    }

    when (val state = uiState) {
        is AddTransactionUiState.Success -> AddTransactionLayout(
            state = state.state,
            onBack = onBack,
            onTypeChanged = viewModel::onTypeChanged,
            onNumberClick = viewModel::onNumberClick,
            onNoteChanged = viewModel::onNoteChanged,
            onSaveClick = viewModel::onSaveClick
        )
        is AddTransactionUiState.Error -> TODO()
        AddTransactionUiState.Loading -> {
            Text("Loading")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTransactionLayout(
    state: AddTransactionState,
    onBack: () -> Unit,
    onTypeChanged: (TransactionType) -> Unit,
    onNumberClick: (String) -> Unit,
    onNoteChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyBalanceTheme.colors.background)
            .imePadding()
    ) {

        AddTransactionToolbar(onBackClick = onBack, onSaveClick = onSaveClick)

        Column(
            modifier = modifier
                .weight(1f)
                .padding(horizontal = MyBalanceTheme.spacing.medium)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(MyBalanceTheme.spacing.medium))

            val transactionType = state.transactionType

            TransactionTypeSelector(
                selected = transactionType,
                onSelected = onTypeChanged
            )

            Text(
                text = state.amount,
                style = MyBalanceTheme.typography.balance,
                color = MyBalanceTheme.colors.textPrimary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(MyBalanceTheme.spacing.small))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(MyBalanceTheme.spacing.medium)
            ) {

                Column(Modifier.background(MyBalanceTheme.colors.background)) {

                    when (state) {
                        is AddTransactionState.Expense -> {
                            ExpenseLayout(state.destinationCategory, state.sourceAccount)
                        }
                        is AddTransactionState.Income -> {
                            IncomeLayout(state.sourceCategory, state.destinationAccount)
                        }
                        is AddTransactionState.Transfer -> {
                            TransferLayout(state.sourceAccount, state.destinationAccount)
                        }
                    }
                    HorizontalDivider()
                    SettingsRow(state.date.toSettingsItem(), settingsInterfacePreview)
                }
            }

            Spacer(Modifier.height(MyBalanceTheme.spacing.medium))

            OutlinedTextField(
                value = state.note ?: "",
                onValueChange = onNoteChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "Комментарий",
                        style = MyBalanceTheme.typography.description,
                        color = MyBalanceTheme.colors.textSecondary.copy(alpha = 0.5f)
                    )
                },
                label = {
                    Text(
                        "Заметка",
                        style = MyBalanceTheme.typography.description,
                        color = MyBalanceTheme.colors.brand
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MyBalanceTheme.colors.textPrimary,
                    unfocusedTextColor = MyBalanceTheme.colors.textPrimary,
                    cursorColor = MyBalanceTheme.colors.brand,
                    focusedBorderColor = MyBalanceTheme.colors.brand,
                    unfocusedBorderColor = MyBalanceTheme.colors.textSecondary.copy(alpha = 0.5f)
                )
            )

            Spacer(Modifier.height(MyBalanceTheme.spacing.medium))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                state.tags.forEach {
                    TagChip(it)
                }

                AssistChip(onClick = {}, label = {
                    Text("+ Тег")
                })
            }
        }
        if (!WindowInsets.isImeVisible) {
            NumberKeyboard(onKeyClick = onNumberClick)
        }
    }
}

@Composable
fun ExpenseLayout(destinationCategory: CategoryDTO, sourceAccount: AccountDTO) {
    SettingsRow(destinationCategory.toSettingsItem(), settingsInterfacePreview)
    HorizontalDivider()
    SettingsRow(sourceAccount.toSettingsItem(), settingsInterfacePreview)
}

@Composable
fun IncomeLayout(sourceCategory: CategoryDTO, destinationAccount: AccountDTO) {
    SettingsRow(sourceCategory.toSettingsItem(), settingsInterfacePreview)
    HorizontalDivider()
    SettingsRow(destinationAccount.toSettingsItem(), settingsInterfacePreview)
}

@Composable
fun TransferLayout(sourceAccount: AccountDTO, destinationAccount: AccountDTO) {
    SettingsRow(sourceAccount.toSettingsItem(), settingsInterfacePreview)
    HorizontalDivider()
    SettingsRow(destinationAccount.toSettingsItem(), settingsInterfacePreview)
}

@Preview
@Composable
fun AddTransactionLayoutPreview(modifier: Modifier = Modifier) {
    MyBalanceTheme {
        AddTransactionLayout(
            state = addTransactionStatePreview,
            onBack = {},
            onTypeChanged = {},
            onNumberClick = {},
            onNoteChanged = {},
            onSaveClick = {},
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun AddTransactionLayoutPreviewDark(modifier: Modifier = Modifier) {
    MyBalanceTheme(darkTheme = true) {
        AddTransactionLayout(
            state = addTransactionStatePreview,
            onBack = {},
            onTypeChanged = {},
            onNumberClick = {},
            onNoteChanged = {},
            onSaveClick = {},
            modifier = modifier
        )
    }
}
