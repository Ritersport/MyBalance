package com.ritesrport.core.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IncomeSourceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val icon: String
)