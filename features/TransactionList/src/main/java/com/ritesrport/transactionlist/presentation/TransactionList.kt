package com.ritesrport.transactionlist.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ritesrport.core.designsystem.theme.MyBalanceTheme
import com.ritesrport.items.transactioncard.TransactionCard
import com.ritesrport.items.transactioncard.TransactionInterface
import com.ritesrport.items.transactioncard.transactionInterfacePreview
import com.ritesrport.transactionlist.presentation.composables.TransactionListDateHeader
import com.ritesrport.transactionlist.presentation.composables.TransactionListEmptyLayout
import com.ritesrport.transactionlist.presentation.composables.TransactionListErrorLayout
import com.ritesrport.transactionlist.presentation.composables.TransactionListToolbar
import com.ritesrport.transactionlist.presentation.composables.TransactionTypeFilterRow

@Composable
fun TransactionListScreen(
    viewModel: TransactionListViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TransactionListLayout(state, viewModel, viewModel::onFilterChanged)
}

@Composable
fun TransactionListLayout(
    state: TransactionListUiState,
    transactionCardInterface: TransactionInterface,
    onFilterSelected: (TransactionTypeFilter) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyBalanceTheme.colors.background)
            .statusBarsPadding()
    ) {
        TransactionListToolbar()

        when (state) {
            is TransactionListUiState.Error -> TransactionListErrorLayout(state.throwable)
            TransactionListUiState.Loading -> Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is TransactionListUiState.Empty -> TransactionListEmptyLayout()
            is TransactionListUiState.Success -> {
                TransactionTypeFilterRow(
                    selectedFilter = state.currentFilter, onFilterSelected = onFilterSelected
                )
                TransactionList(
                    groups = state.groups, transactionCardInterface = transactionCardInterface
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionList(
    groups: List<TransactionGroup>,
    transactionCardInterface: TransactionInterface,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        groups.forEach { group ->
            stickyHeader {
                TransactionListDateHeader(group.date)
            }
            items(group.items) { item ->
                TransactionCard(
                    transaction = item,
                    transactionInterface = transactionCardInterface,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun TransactionListLayoutPreviewEmpty() {
    MyBalanceTheme {
        TransactionListLayout(TransactionListUiState.Empty, transactionInterfacePreview) {}
    }
}


@Preview
@Composable
fun TransactionListLayoutPreview() {
    MyBalanceTheme {
        TransactionListLayout(transactionListPreview, transactionInterfacePreview) {}
    }
}

@Preview
@Composable
fun TransactionListLayoutPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        TransactionListLayout(transactionListPreview, transactionInterfacePreview) {}
    }
}
