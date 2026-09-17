package com.ritesrport.features.addtransaction.presentation.model

import androidx.compose.runtime.Stable
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.Bus
import com.ritesrport.features.addtransaction.domain.TransactionType
import com.ritesrport.features.addtransaction.presentation.mappers.toPresentation
import java.util.Calendar

@Stable
sealed class AddTransactionState(
    open val amount: String,
    open val date: Calendar,
    open val note: String?,
    val transactionType: TransactionType,
    open val tags: List<String>
) {
    data class Expense(
        val destinationCategory: CategoryModel,
        val sourceAccount: AccountModel,
        override val tags: List<String>,
        override val amount: String,
        override val date: Calendar,
        override val note: String?,
    ) : AddTransactionState(amount, date, note, TransactionType.EXPENSE, tags)

    data class Income(
        val sourceCategory: CategoryModel,
        val destinationAccount: AccountModel,
        override val tags: List<String>,
        override val amount: String,
        override val date: Calendar,
        override val note: String?,
    ) : AddTransactionState(amount, date, note, TransactionType.INCOME, tags)

    data class Transfer(
        val sourceAccount: AccountModel,
        val destinationAccount: AccountModel,
        override val tags: List<String>,
        override val amount: String,
        override val date: Calendar,
        override val note: String?,
    ) : AddTransactionState(amount, date, note, TransactionType.TRANSFER, tags)

    fun copyState(
        amount: String = this.amount,
        date: Calendar = this.date,
        note: String? = this.note,
        tags: List<String> = this.tags
    ): AddTransactionState {
        return when (this) {
            is Expense -> this.copy(amount = amount, date = date, note = note, tags = tags)
            is Income -> this.copy(amount = amount, date = date, note = note, tags = tags)
            is Transfer -> this.copy(amount = amount, date = date, note = note, tags = tags)
        }
    }

    companion object {
        fun buildDefault(
            defaultCategory: CategoryDTO,
            defaultAccount: AccountDTO
        ): Expense {
            return Expense(
                defaultCategory.toPresentation(),
                defaultAccount.toPresentation(),
                emptyList(),
                "0", //TODO design
                Calendar.getInstance(),
                null
            )
        }

        fun build(
            type: TransactionType,
            category: CategoryDTO,
            account: AccountDTO,
            amount: String = "0",
            date: Calendar = Calendar.getInstance(),
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
    date = Calendar.getInstance(),
    note = null,
)

