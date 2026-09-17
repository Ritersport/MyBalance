package com.ritesrport.transactionlist.presentation.mappers

import androidx.compose.ui.graphics.Color
import com.ritersport.sharedRepository.transactionRepository.dto.TransactionDTO
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.items.transactioncard.TransactionModel
import com.ritesrport.items.transactioncard.TransactionType

fun TransactionDTO.toPresentation(): TransactionModel {
    return when(this) {
        is TransactionDTO.Expense -> toPresentation()
        is TransactionDTO.Income -> toPresentation()
        is TransactionDTO.Transfer -> toPresentation()
    }
}

private fun TransactionDTO.Income.toPresentation(): TransactionModel {
    return TransactionModel(
        id,
        comment ?: sourceCategory.name,
        sourceCategory.name,
        amount.toPlainString(), //TODO: DesignSystem
        TransactionType.INCOME,
        destinationAccount.name,
        MyBalanceIcons.from(sourceCategory.iconKey),
        Color(12, 24, 136, 255),
        date
    )
}

private fun TransactionDTO.Expense.toPresentation(): TransactionModel {
    return TransactionModel(
        id,
        comment ?: destinationCategory.name,
        destinationCategory.name,
        amount.toPlainString(), //TODO: DesignSystem
        TransactionType.EXPENSE,
        sourceAccount.name,
        MyBalanceIcons.from(destinationCategory.iconKey),
        Color(12, 24, 136, 255),
        date
    )
}

private fun TransactionDTO.Transfer.toPresentation(): TransactionModel {
    return TransactionModel(
        id,
        comment ?: sourceAccount.name,
        sourceAccount.name,
        amount.toPlainString(), //TODO: DesignSystem
        TransactionType.EXPENSE,
        destinationAccount.name,
        MyBalanceIcons.from(sourceAccount.iconKey),
        Color(12, 24, 136, 255),
        date
    )
}