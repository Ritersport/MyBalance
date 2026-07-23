package com.ritesrport.features.addtransaction.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesrport.features.addtransaction.domain.TransactionType.EXPENSE
import com.ritesrport.features.addtransaction.presentation.composables.AddTransactionToolbar
import com.ritesrport.features.addtransaction.presentation.composables.NumberKeyboard
import com.ritesrport.features.addtransaction.presentation.composables.SettingsRow
import com.ritesrport.features.addtransaction.presentation.composables.TagChip
import com.ritesrport.features.addtransaction.presentation.composables.TransactionTypeSelector

@Composable
fun AddTransactionScreen() {

}

@Composable
fun AddTransactionLayout(state: AddTransactionState, modifier: Modifier = Modifier) {
    var selectedType by remember {
        mutableStateOf(EXPENSE)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFFF6F6F6))
            .verticalScroll(rememberScrollState())
    ) {

        AddTransactionToolbar(
            onBackClick = {},
            onSaveClick = {}
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(Modifier.height(12.dp))

            TransactionTypeSelector(
                selected = selectedType,
                onSelected = {
                    selectedType = it
                }
            )

            Spacer(Modifier.height(28.dp))

            Text(
                text = state.amount,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(28.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column {

                    SettingsRow(state.source)

                    HorizontalDivider()

                    SettingsRow(state.destination)

                    HorizontalDivider()

                    SettingsRow(state.date)
                }
            }

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = "Обед в ресторане",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Заметка")
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Теги",
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(Modifier.height(8.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                state.tags.forEach {
                    TagChip(it)
                }

                AssistChip(
                    onClick = {},
                    label = {
                        Text("+ Тег")
                    }
                )
            }
            NumberKeyboard()
        }
    }
}

@Preview
@Composable
fun AddTransactionLayoutPreview(modifier: Modifier = Modifier) {
    AddTransactionLayout(
        state = addTransactionStatePreview,
        modifier = modifier
    )
}