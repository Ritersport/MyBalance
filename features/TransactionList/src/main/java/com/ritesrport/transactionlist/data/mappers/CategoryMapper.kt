package com.ritesrport.transactionlist.data.mappers

import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import com.ritesrport.core.model.IconKey
import com.ritesrport.transactionlist.domain.Category

fun IncomeSourceEntity.toDomain(): Category {
    return Category(id, name, IconKey.getFromValue(icon))
}

fun ExpenseCategoryEntity.toDomain(): Category {
    return Category(id, name, IconKey.getFromValue(icon))
}