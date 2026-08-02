package com.ritesrport.features.addtransaction.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.Check
import com.ritesrport.core.designsystem.icons.Close

@Composable
fun AddTransactionToolbar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {

        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                MyBalanceIcons.Close,
                contentDescription = "Закрыть",
                tint = Color(0xFF6A5AE0)
            )
        }

        Text(
            text = "Новая транзакция",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )

        IconButton(
            onClick = onSaveClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                MyBalanceIcons.Check,
                contentDescription = "Сохранить",
                tint = Color(0xFF6A5AE0)
            )
        }
    }
}

@Preview
@Composable
fun AddTransactionToolbarPreview() {
    AddTransactionToolbar({}, {})
}
