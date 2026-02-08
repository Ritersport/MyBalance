package com.ritesrport.mybalance.domain.entity

import java.math.BigDecimal

data class Account(
    val id: Long,
    val name: String,
    val initialBalance: BigDecimal
)