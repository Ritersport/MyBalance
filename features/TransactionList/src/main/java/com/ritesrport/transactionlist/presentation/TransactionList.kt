package com.ritesrport.transactionlist.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ritesrport.items.transactioncard.TransactionCard
import com.ritesrport.items.transactioncard.TransactionInterface
import com.ritesrport.items.transactioncard.TransactionModel
import com.ritesrport.items.transactioncard.transactionInterfacePreview
import com.ritesrport.items.transactioncard.transactionModelExpensePreview
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ritesrport.core.designsystem.theme.MyBalanceTheme
import com.ritesrport.items.transactioncard.transactionModelIncomePreview

@Composable
fun TransactionListScreen(
    viewModel: TransactionListViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    when (state) {
        is TransactionListUiState.Error -> TODO()
        TransactionListUiState.Loading -> Text("Loading...")
        is TransactionListUiState.Success -> TransactionListLayout(
            (state as TransactionListUiState.Success).items,
            viewModel
        )
    }
}

@Composable
fun TransactionListLayout(
    transactionItems: List<TransactionModel>,
    transactionCardInterface: TransactionInterface,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(transactionItems) { index, item ->
            TransactionCard(
                item,
                transactionCardInterface,
            )
            if (transactionItems.lastIndex != index) {
                HorizontalDivider()
            }
        }
    }
}

@Preview
@Composable
fun TransactionListLayoutPreview() {
    MyBalanceTheme {
        TransactionListLayout(
            listOf(
                transactionModelExpensePreview,
                transactionModelIncomePreview,
                transactionModelExpensePreview
            ),
            transactionInterfacePreview
        )
    }
}

@Preview
@Composable
fun TransactionListLayoutPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        TransactionListLayout(
            listOf(
                transactionModelExpensePreview,
                transactionModelIncomePreview,
                transactionModelExpensePreview
            ),
            transactionInterfacePreview
        )
    }
}
