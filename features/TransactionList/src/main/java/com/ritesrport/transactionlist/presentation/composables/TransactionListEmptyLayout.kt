package com.ritesrport.transactionlist.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ritesrport.core.designsystem.theme.MyBalanceTheme

@Composable
fun TransactionListEmptyLayout() {
    Box(
        Modifier
            .background(MyBalanceTheme.colors.background)
            .fillMaxSize()
    ) {
        Text(
            text = "Пока нет ни одной записи",
            style = MyBalanceTheme.typography.caption,
            color = MyBalanceTheme.colors.textPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun EmptyLayoutPreview() {
    MyBalanceTheme {
        TransactionListEmptyLayout()
    }
}

@Preview
@Composable
fun EmptyLayoutPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        TransactionListEmptyLayout()
    }
}