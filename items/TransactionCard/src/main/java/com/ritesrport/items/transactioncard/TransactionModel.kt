package com.ritesrport.items.transactioncard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
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

val transactionModelPreview = TransactionModel(
    0,
    "Автобус",
    "Транспорт",
    "-Р 45,00",
    TransactionType.EXPENSE,
    "Карта Тинькофф",
    Icons.Default.ShoppingCart,
    Color(12, 24, 136, 255)
)