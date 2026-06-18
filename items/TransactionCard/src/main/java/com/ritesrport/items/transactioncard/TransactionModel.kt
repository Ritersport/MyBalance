package com.ritesrport.items.transactioncard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class TransactionModel(
    val id: Long,
    val title: String,
    val category: String,
    val amount: String,
    val type: TransactionType,
    val additionalInfo: String,
    val icon: ImageVector,
    val iconColor: Color
)

enum class TransactionType {
    INCOME,
    EXPENSE,
    TRANSFER
}
