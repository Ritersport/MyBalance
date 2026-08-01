package com.ritesrport.features.addtransaction.presentation

import androidx.compose.runtime.Stable
import com.ritesrport.features.addtransaction.domain.TransactionType
import com.ritesrport.features.addtransaction.presentation.model.SettingsItem
import com.ritesrport.features.addtransaction.presentation.model.settingsItemPreview

@Stable
data class AddTransactionState(
    val transactionType: TransactionType,
    val amount: String,
    val source: SettingsItem,
    val destination: SettingsItem,
    val date: SettingsItem,
    val tags: List<String>
)

val addTransactionStatePreview = AddTransactionState(
    TransactionType.EXPENSE,
    "17.000",
    settingsItemPreview,
    settingsItemPreview,
    settingsItemPreview,
    listOf("обед", "ресторан")
)

