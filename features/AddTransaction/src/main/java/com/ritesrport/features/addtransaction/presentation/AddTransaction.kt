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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ritesrport.core.designsystem.theme.MyBalanceTheme
import com.ritesrport.features.addtransaction.presentation.composables.AddTransactionToolbar
import com.ritesrport.features.addtransaction.presentation.composables.NumberKeyboard
import com.ritesrport.features.addtransaction.presentation.composables.SettingsRow
import com.ritesrport.features.addtransaction.presentation.composables.TagChip
import com.ritesrport.features.addtransaction.presentation.composables.TransactionTypeSelector

@Composable
fun AddTransactionScreen(
    onBack: () -> Unit,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is AddTransactionUiState.Success -> AddTransactionLayout(
            state = state.state,
            onBack = onBack,
            onTypeChanged = viewModel::onTypeChanged,
            onNumberClick = viewModel::onNumberClick
        )
        is AddTransactionUiState.Error -> TODO()
        AddTransactionUiState.Loading -> {
            AddTransactionLayout(
                state = AddTransactionState.default,
                onBack = onBack,
                onTypeChanged = {},
                onNumberClick = {}
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTransactionLayout(
    state: AddTransactionState,
    onBack: () -> Unit,
    onTypeChanged: (com.ritesrport.features.addtransaction.domain.TransactionType) -> Unit,
    onNumberClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyBalanceTheme.colors.background)
            .imePadding()
    ) {

        AddTransactionToolbar(onBackClick = onBack, onSaveClick = {})

        Column(
            modifier = modifier
                .weight(1f)
                .padding(horizontal = MyBalanceTheme.spacing.medium)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(MyBalanceTheme.spacing.medium))

            TransactionTypeSelector(
                selected = state.transactionType,
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

                    SettingsRow(state.source)

                    HorizontalDivider()

                    SettingsRow(state.destination)

                    HorizontalDivider()

                    SettingsRow(state.date)
                }
            }

            Spacer(Modifier.height(MyBalanceTheme.spacing.medium))

            OutlinedTextField(
                value = "Обед в ресторане",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
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

@Preview
@Composable
fun AddTransactionLayoutPreview(modifier: Modifier = Modifier) {
    MyBalanceTheme {
        AddTransactionLayout(
            state = addTransactionStatePreview,
            onBack = {},
            onTypeChanged = {},
            onNumberClick = {},
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
            modifier = modifier
        )
    }
}
