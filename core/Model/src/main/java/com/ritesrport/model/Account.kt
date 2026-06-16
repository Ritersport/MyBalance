package com.ritesrport.model

import java.math.BigDecimal

data class Account(
    val id: Long,
    val name: String,
    val initialBalance: BigDecimal,
    val icon: IconKey
)