package com.ritersport.sharedRepository.transactionRepository.dto

import java.math.BigDecimal
import java.time.LocalDate

sealed class TransactionDTO(
    val id: Long = 0,
    val title: String,
    val amount: BigDecimal,
    val date: LocalDate,
    val comment: String?
) {
    class Expense(
        id: Long = 0,
        title: String,
        amount: BigDecimal,
        date: LocalDate,
        val sourceAccount: AccountDTO,
        val destinationCategory: CategoryDTO,
        comment: String?
    ) : TransactionDTO(id, title, amount, date, comment)

    class Income(
        id: Long = 0,
        title: String,
        amount: BigDecimal,
        date: LocalDate,
        val sourceCategory: CategoryDTO,
        val destinationAccount: AccountDTO,
        comment: String?
    ) : TransactionDTO(id, title, amount, date, comment)

    class Transfer(
        id: Long = 0,
        title: String,
        amount: BigDecimal,
        date: LocalDate,
        val sourceAccount: AccountDTO,
        val destinationAccount: AccountDTO,
        comment: String?
    ) : TransactionDTO(id, title, amount, date, comment)
}