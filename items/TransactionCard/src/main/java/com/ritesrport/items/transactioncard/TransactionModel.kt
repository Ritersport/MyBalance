package com.ritesrport.items.transactioncard

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class TransactionModel(
    val id: Long,
    val title: String,
    val category: String,
    val amount: String,
    val type: TransactionType,
    val additionalInfo: String,
    @param:DrawableRes val iconResId: Int,
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
    R.drawable.category_shopping_cart,
    Color(12, 24, 136, 255)
)