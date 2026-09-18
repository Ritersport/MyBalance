package com.ritesrport.core.database.data.db

import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity

interface DefaultCategoriesProvider {
    val expenseCategories: List<ExpenseCategoryEntity>
    val incomeSources: List<IncomeSourceEntity>
}
