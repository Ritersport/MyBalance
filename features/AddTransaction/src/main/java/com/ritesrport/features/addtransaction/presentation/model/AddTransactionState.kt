package com.ritesrport.features.addtransaction.presentation.model

import androidx.compose.runtime.Stable
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.Bus
import com.ritesrport.features.addtransaction.domain.TransactionType
import com.ritesrport.features.addtransaction.presentation.mappers.toPresentation
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
        val destinationCategory: CategoryModel,
        val sourceAccount: AccountModel,
        override val tags: List<String>,
        override val amount: String,
        override val date: LocalDate,
        override val note: String?,
    ) : AddTransactionState(amount, date, note, TransactionType.EXPENSE, tags)

    data class Income(
        val sourceCategory: CategoryModel,
        val destinationAccount: AccountModel,
        override val tags: List<String>,
        override val amount: String,
        override val date: LocalDate,
        override val note: String?,
    ) : AddTransactionState(amount, date, note, TransactionType.INCOME, tags)

    data class Transfer(
        val sourceAccount: AccountModel,
        val destinationAccount: AccountModel,
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
                    destinationCategory = category.toPresentation(),
                    sourceAccount = account.toPresentation(),
                    tags = emptyList(),
                    amount = amount,
                    date = date,
                    note = note
                )
                TransactionType.INCOME -> Income(
                    sourceCategory = category.toPresentation(),
                    destinationAccount = account.toPresentation(),
                    tags = emptyList(),
                    amount = amount,
                    date = date,
                    note = note
                )
                TransactionType.TRANSFER -> Transfer(
                    sourceAccount = account.toPresentation(),
                    destinationAccount = account.toPresentation(),
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
    destinationCategory = CategoryModel(0, "Еда", MyBalanceIcons.Bus),
    sourceAccount = AccountModel(0, "Карта", MyBalanceIcons.Bus),
    tags = listOf("обед", "ресторан"),
    amount = "17.000",
    date = LocalDate.now(),
    note = null,
)

