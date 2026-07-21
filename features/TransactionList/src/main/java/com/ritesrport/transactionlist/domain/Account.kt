package com.ritesrport.transactionlist.domain

import com.ritesrport.core.model.IconKey
import java.math.BigDecimal

data class Account(
    val id: Long,
    val name: String,
    val balance: BigDecimal,
    val icon: IconKey
)