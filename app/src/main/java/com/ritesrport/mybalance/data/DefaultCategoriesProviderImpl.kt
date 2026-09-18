package com.ritesrport.mybalance.data

import com.ritesrport.core.database.data.db.DefaultCategoriesProvider
import com.ritesrport.core.database.data.entity.ExpenseCategoryEntity
import com.ritesrport.core.database.data.entity.IncomeSourceEntity
import com.ritesrport.core.model.IconKey
import javax.inject.Inject

class DefaultCategoriesProviderImpl @Inject constructor() : DefaultCategoriesProvider {
    override val expenseCategories = listOf(
        ExpenseCategoryEntity(0, "Продукты", IconKey.ShoppingCart.value, true),
        ExpenseCategoryEntity(0, "Транспорт", IconKey.Bus.value, true),
        ExpenseCategoryEntity(0, "Здоровье", IconKey.Check.value, true)
    )

    override val incomeSources = listOf(
        IncomeSourceEntity(0, "Зарплата", IconKey.DateRange.value, true),
        IncomeSourceEntity(0, "Перевод", IconKey.Close.value, true)
    )
}
