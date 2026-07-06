package com.ritesrport.core.database.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithRelations(
    @Embedded val transaction: TransactionEntity,

    @Relation(
        parentColumn = "sourceAccountId",
        entityColumn = "id"
    )
    val sourceAccount: AccountEntity?,

    @Relation(
        parentColumn = "destinationAccountId",
        entityColumn = "id"
    )
    val destinationAccount: AccountEntity?,

    @Relation(
        parentColumn = "sourceCategoryId",
        entityColumn = "id"
    )
    val sourceCategory: IncomeSourceEntity?,

    @Relation(
        parentColumn = "destinationCategoryId",
        entityColumn = "id"
    )
    val destinationCategory: ExpenseCategoryEntity?
)
