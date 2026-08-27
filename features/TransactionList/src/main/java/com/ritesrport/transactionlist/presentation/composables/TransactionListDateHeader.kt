package com.ritesrport.transactionlist.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ritesrport.core.designsystem.theme.MyBalanceTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TransactionListDateHeader(date: LocalDate) {
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.forLanguageTag("ru"))
    val dateText = when (date) {
        LocalDate.now() -> "Сегодня, ${date.format(formatter)}"
        LocalDate.now().minusDays(1) -> "Вчера, ${date.format(formatter)}"
        else -> date.format(formatter)
    }

    Text(
        text = dateText,
        style = MyBalanceTheme.typography.description,
        color = MyBalanceTheme.colors.textSecondary,
        modifier = Modifier
            .fillMaxWidth()
            .background(MyBalanceTheme.colors.surface)
            .padding(
                horizontal = MyBalanceTheme.spacing.medium,
                vertical = MyBalanceTheme.spacing.small
            )
    )
}

@Preview
@Composable
fun TransactionListDateHeaderPreview() {
    MyBalanceTheme {
        TransactionListDateHeader(LocalDate.now())
    }
}

@Preview
@Composable
fun TransactionListDateHeaderPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        TransactionListDateHeader(LocalDate.now())
    }
}