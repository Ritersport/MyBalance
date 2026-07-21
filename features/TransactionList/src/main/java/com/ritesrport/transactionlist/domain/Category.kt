package com.ritesrport.transactionlist.domain

import com.ritesrport.core.model.IconKey

data class Category(
    val id: Long,
    val name: String,
    val icon: IconKey
)
