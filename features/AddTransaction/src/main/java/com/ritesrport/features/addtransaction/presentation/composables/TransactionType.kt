package com.ritesrport.features.addtransaction.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesrport.features.addtransaction.domain.TransactionType

@Composable
fun TransactionTypeSelector(
    selected: TransactionType,
    onSelected: (TransactionType) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            32.dp,
            alignment = Alignment.CenterHorizontally
        ),

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
        onClick = onClick,
        label = {
            Text(text, modifier = Modifier.padding(horizontal = 6.dp),)
        }
    )
}

@Preview
@Composable
fun TransactionTypeSelectorPreview() {
    TransactionTypeSelector(TransactionType.EXPENSE) { }
}