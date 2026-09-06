package com.ritersport.sharedRepository.transactionRepository.dto

import com.ritesrport.core.model.IconKey

data class AccountDTO(
    val id: Long,
    val name: String,
    val iconKey: IconKey,
    val balance: Double = 0.0
)
