package com.ritesrport.mybalance.domain.entity

import java.math.BigDecimal
import java.time.LocalDate

sealed class Transaction(
    open val id: Long,
    open val amount: BigDecimal,
    open val date: LocalDate,
    open val comment: String?
)

data class Income(
    override val id: Long,
    override val amount: BigDecimal,
    override val date: LocalDate,
    val source: Category,
    val account: Account,
    override val comment: String?
): Transaction(id, amount, date, comment)

data class Expense(
    override val id: Long,
    override val amount: BigDecimal,
    override val date: LocalDate,
    val category: Category,
    val account: Account,
    override val comment: String?
): Transaction(id, amount, date, comment)

data class Transfer(
    override val id: Long,
    override val amount: BigDecimal,
    override val date: LocalDate,
    val sourceAccount: Account,
    val destinationAccount: Account,
    override val comment: String?
): Transaction(id, amount, date, comment)