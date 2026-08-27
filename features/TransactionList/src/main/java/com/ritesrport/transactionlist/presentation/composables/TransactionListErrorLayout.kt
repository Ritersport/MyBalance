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
fun TransactionListErrorLayout(throwable: Throwable) {
    Box(
        Modifier
            .background(MyBalanceTheme.colors.background)
            .fillMaxSize()
    ) {
        Text(
            text = "${throwable.message}",
            style = MyBalanceTheme.typography.caption,
            color = MyBalanceTheme.colors.textPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun ErrorLayoutPreview() {
    MyBalanceTheme {
        TransactionListErrorLayout(Throwable("Something went wrong :("))
    }
}

@Preview
@Composable
fun ErrorLayoutPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        TransactionListErrorLayout(Throwable("Something went wrong :("))
    }
}