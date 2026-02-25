package com.ritesrport.mybalance.presentation.transactions.model

import com.ritesrport.mybalance.domain.entity.Account
import com.ritesrport.mybalance.domain.entity.Category
import com.ritesrport.mybalance.presentation.model.UiIcon
import com.ritesrport.mybalance.presentation.model.toUiIcon
import java.math.BigDecimal

data class TransactionItemUi(
    val id: Long,
    val amount: BigDecimal,
    val source: CategoryItem,
    val destination: CategoryItem,
    val comment: String?
)

data class CategoryItem(
    val id: Long, val name: String, val icon: UiIcon
)

fun Account.toCategoryItem(): CategoryItem = CategoryItem(id, name, icon.toUiIcon())
fun Category.toCategoryItem(): CategoryItem = CategoryItem(id, name, icon.toUiIcon())