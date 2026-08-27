package com.ritesrport.transactionlist.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ritesrport.core.designsystem.theme.MyBalanceTheme
import com.ritesrport.transactionlist.presentation.TransactionTypeFilter

@Composable
fun TransactionTypeFilterRow(
    selectedFilter: TransactionTypeFilter,
    onFilterSelected: (TransactionTypeFilter) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MyBalanceTheme.spacing.medium,
                vertical = MyBalanceTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
    ) {
        TransactionTypeFilter.entries.forEach { filter ->
            val isSelected = filter == selectedFilter
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(MyBalanceTheme.spacing.medium))
                    .background(
                        if (isSelected) MyBalanceTheme.colors.brand.copy(alpha = 0.1f)
                        else Color.Transparent
                    )
                    .clickable { onFilterSelected(filter) }
                    .padding(
                        horizontal = MyBalanceTheme.spacing.medium,
                        vertical = MyBalanceTheme.spacing.small
                    )
            ) {
                Text(
                    text = when (filter) {
                        TransactionTypeFilter.ALL -> "Все"
                        TransactionTypeFilter.INCOME -> "Доходы"
                        TransactionTypeFilter.EXPENSE -> "Расходы"
                        TransactionTypeFilter.TRANSFER -> "Переводы"
                    },
                    style = MyBalanceTheme.typography.caption,
                    color = if (isSelected) MyBalanceTheme.colors.brand else MyBalanceTheme.colors.textSecondary
                )
            }
        }
    }
}

@Preview
@Composable
fun TransactionTypeFilterRowPreview() {
    MyBalanceTheme {
        TransactionTypeFilterRow(TransactionTypeFilter.ALL) {}
    }
}

@Preview
@Composable
fun TransactionTypeFilterRowPreviewDark() {
    MyBalanceTheme(darkTheme = false) {
        TransactionTypeFilterRow(TransactionTypeFilter.TRANSFER) {}
    }
}