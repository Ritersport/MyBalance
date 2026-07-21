package com.ritesrport.transactionlist.data.mappers

import com.ritesrport.core.database.data.entity.AccountEntity
import com.ritesrport.core.model.IconKey
import com.ritesrport.transactionlist.domain.Account
import java.math.BigDecimal

fun AccountEntity.toDomain(): Account {
    return Account(id, name, balance ?: BigDecimal(0), IconKey.getFromValue(icon))
}