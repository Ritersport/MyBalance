package com.ritesrport.features.addtransaction.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ritesrport.core.designsystem.theme.MyBalanceTheme
import com.ritesrport.features.addtransaction.domain.TransactionType

@Composable
fun TransactionTypeSelector(
    selected: TransactionType,
    onSelected: (TransactionType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyBalanceTheme.colors.background),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        TransactionChip(
            text = "Расход",
            selected = selected == TransactionType.EXPENSE
        ) {
            onSelected(TransactionType.EXPENSE)
        }

        TransactionChip(
            text = "Доход",
            selected = selected == TransactionType.INCOME
        ) {
            onSelected(TransactionType.INCOME)
        }

        TransactionChip(
            text = "Перевод",
            selected = selected == TransactionType.TRANSFER
        ) {
            onSelected(TransactionType.TRANSFER)
        }
    }
}

@Composable
private fun TransactionChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        shape = RoundedCornerShape(50),
        colors = FilterChipDefaults.filterChipColors()
            .copy(containerColor = MyBalanceTheme.colors.surface),
        onClick = onClick,
        label = {
            Text(
                text,
                color = MyBalanceTheme.colors.textPrimary,
                style = MyBalanceTheme.typography.description,
                modifier = Modifier.padding(horizontal = MyBalanceTheme.spacing.small),
            )
        }
    )
}

@Preview
@Composable
fun TransactionTypeSelectorPreview() {
    MyBalanceTheme {
        TransactionTypeSelector(TransactionType.EXPENSE) { }
    }
}

@Preview
@Composable
fun TransactionTypeSelectorPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        TransactionTypeSelector(TransactionType.EXPENSE) { }
    }
}