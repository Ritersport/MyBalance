package com.ritesrport.features.addtransaction.presentation.model

import androidx.compose.runtime.Stable
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritesrport.core.model.IconKey
import com.ritesrport.features.addtransaction.domain.TransactionType
import java.time.LocalDate

@Stable
sealed class AddTransactionState(
    open val amount: String,
    open val date: LocalDate,
    open val note: String?,
    val transactionType: TransactionType,
    open val tags: List<String>
) {
    data class Expense(
        val destinationCategory: CategoryDTO,
        val sourceAccount: AccountDTO,
        override val tags: List<String>,
        override val amount: String,
        override val date: LocalDate,
        override val note: String?,
    ) : AddTransactionState(amount, date, note, TransactionType.EXPENSE, tags)

    data class Income(
        val sourceCategory: CategoryDTO,
        val destinationAccount: AccountDTO,
        override val tags: List<String>,
        override val amount: String,
        override val date: LocalDate,
        override val note: String?,
    ) : AddTransactionState(amount, date, note, TransactionType.INCOME, tags)

    data class Transfer(
        val sourceAccount: AccountDTO,
        val destinationAccount: AccountDTO,
        override val tags: List<String>,
        override val amount: String,
        override val date: LocalDate,
        override val note: String?,
    ) : AddTransactionState(amount, date, note, TransactionType.TRANSFER, tags)

    companion object {
        fun build(
            type: TransactionType,
            category: CategoryDTO,
            account: AccountDTO,
            amount: String = "0",
            date: LocalDate = LocalDate.now(),
            note: String? = null
        ): AddTransactionState {
            return when (type) {
                TransactionType.EXPENSE -> Expense(
                    destinationCategory = category,
                    sourceAccount = account,
                    tags = emptyList(),
                    amount = amount,
                    date = date,
                    note = note
                )
                TransactionType.INCOME -> Income(
                    sourceCategory = category,
                    destinationAccount = account,
                    tags = emptyList(),
                    amount = amount,
                    date = date,
                    note = note
                )
                TransactionType.TRANSFER -> Transfer(
                    sourceAccount = account,
                    destinationAccount = account,
                    tags = emptyList(),
                    amount = amount,
                    date = date,
                    note = note
                )
            }
        }
    }
}

val addTransactionStatePreview = AddTransactionState.Expense(
    destinationCategory = CategoryDTO(0, "Еда", IconKey.Bus),
    sourceAccount = AccountDTO(0, "Карта", IconKey.Bus),
    tags = listOf("обед", "ресторан"),
    amount = "17.000",
    date = LocalDate.now(),
    note = null,
)

