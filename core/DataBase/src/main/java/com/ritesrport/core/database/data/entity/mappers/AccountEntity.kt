package com.ritesrport.core.database.data.entity.mappers

import com.ritesrport.core.database.data.entity.AccountEntity
import com.ritesrport.model.Account
import com.ritesrport.model.IconKey
import java.math.BigDecimal

fun AccountEntity.toDomain(): Account {
    return Account(id, name, balance ?: BigDecimal(0), IconKey(icon))
}