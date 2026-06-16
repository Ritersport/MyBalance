package com.ritesrport.core.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDate

@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val type: TransactionType,
    val amount: BigDecimal,
    val date: LocalDate,

    val sourceAccountId: Long?,
    val destinationAccountId: Long?,
    val sourceCategoryId: Long?,
    val destinationCategoryId: Long?,

    val comment: String?
)

enum class TransactionType {
    INCOME, EXPENSE, TRANSFER
}
