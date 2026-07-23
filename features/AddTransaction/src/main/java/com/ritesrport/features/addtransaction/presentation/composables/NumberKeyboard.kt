package com.ritesrport.features.addtransaction.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NumberKeyboard() {

    val rows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf(",", "0", "⌫")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(4.dp)
    ) {

        rows.forEach { row ->

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                row.forEach { key ->

                    FilledTonalButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {}
                    ) {
                        Text(
                            key,
                            style = MaterialTheme.typography.headlineSmall
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
    NumberKeyboard()
}