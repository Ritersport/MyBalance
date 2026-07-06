package com.ritesrport.core.database.data.entity.mappers

import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import com.ritesrport.model.Category
import com.ritesrport.model.IconKey

fun IncomeSourceEntity.toDomain(): Category {
    return Category(id, name, IconKey((icon)))
}

fun ExpenseCategoryEntity.toDomain(): Category {
    return Category(id, name, IconKey(icon))
}