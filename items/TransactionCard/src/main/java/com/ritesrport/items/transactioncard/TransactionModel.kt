package com.ritesrport.items.transactioncard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.Check
import com.ritesrport.core.designsystem.icons.ShoppingCart

import java.time.LocalDate

data class TransactionModel(
    val id: Long,
    val title: String,
    val category: String,
    val amount: String,
    val type: TransactionType,
    val additionalInfo: String,
    val icon: ImageVector,
    val iconColor: Color,
    val date: LocalDate
)

enum class TransactionType {
    INCOME,
    EXPENSE,
    TRANSFER
}

val transactionModelExpensePreview = TransactionModel(
    0,
    "Автобус",
    "Транспорт",
    "-Р 45,00",
    TransactionType.EXPENSE,
    "Карта Тинькофф",
    MyBalanceIcons.ShoppingCart,
    Color(12, 24, 136, 255),
    LocalDate.now()
)

val transactionModelIncomePreview = TransactionModel(
    0,
    "Кэшбек",
    "",
    "+P 235,77",
    TransactionType.INCOME,
    "Карта Тинькофф",
    MyBalanceIcons.Check,
    Color(0xFFFFB5D6),
    LocalDate.now()
)