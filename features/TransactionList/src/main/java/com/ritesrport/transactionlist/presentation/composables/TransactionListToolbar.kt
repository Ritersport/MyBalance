package com.ritesrport.transactionlist.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesrport.core.designsystem.theme.MyBalanceTheme

@Composable
fun TransactionListToolbar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(MyBalanceTheme.colors.surface)
            .height(56.dp)
    ) {
        Text(
            text = "Транзакции",
            style = MyBalanceTheme.typography.header,
            color = MyBalanceTheme.colors.textPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun TransactionListToolbarPreview() {
    MyBalanceTheme {
        TransactionListToolbar()
    }
}

@Preview
@Composable
fun TransactionListToolbarPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        TransactionListToolbar()
    }
}