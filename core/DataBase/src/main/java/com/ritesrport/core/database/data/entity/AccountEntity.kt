package com.ritesrport.core.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val initialBalance: BigDecimal,
    val icon: String
)