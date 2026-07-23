package com.ritesrport.features.addtransaction.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritesrport.core.designsystem.icons.close

@Composable
fun TagChip(text: String) {
    InputChip(
        selected = true,
        onClick = {},
        label = {
            Text(text)
        },
        trailingIcon = {
            Icon(
                imageVector = close,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    )
}