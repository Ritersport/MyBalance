package com.ritesrport.features.addtransaction.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ritesrport.core.designsystem.theme.MyBalanceTheme

@Composable
fun NumberKeyboard(
    onKeyClick: (String) -> Unit = {}
) {

    val rows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf(",", "0", "⌫")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyBalanceTheme.colors.background)
            .navigationBarsPadding()
            .padding(MyBalanceTheme.spacing.extraSmall)
    ) {

        rows.forEach { row ->

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                row.forEach { key ->

                    FilledTonalButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(MyBalanceTheme.spacing.extraSmall),
                        shape = RoundedCornerShape(MyBalanceTheme.spacing.small),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = MyBalanceTheme.colors.surface,
                            contentColor = MyBalanceTheme.colors.textPrimary
                        ),
                        onClick = { onKeyClick(key) }
                    ) {
                        Text(
                            key,
                            style = MyBalanceTheme.typography.header
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NumberKeyboardPreview() {
    MyBalanceTheme {
        NumberKeyboard()
    }
}

@Preview
@Composable
fun NumberKeyboardPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        NumberKeyboard()
    }
}