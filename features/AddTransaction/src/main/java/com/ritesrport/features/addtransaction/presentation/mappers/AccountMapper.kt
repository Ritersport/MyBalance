package com.ritesrport.features.addtransaction.presentation.mappers

import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.features.addtransaction.presentation.model.AccountModel

fun AccountDTO.toPresentation(): AccountModel {
    return AccountModel(id, name, MyBalanceIcons.from(iconKey))
}