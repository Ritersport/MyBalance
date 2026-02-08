package com.ritesrport.mybalance.domain.entity

import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(
    val amount: BigDecimal,
    val categoryId: Long,
    val date: LocalDate,
    val comment: String?
)