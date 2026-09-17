package com.ritesrport.features.addtransaction.presentation.mappers

import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.features.addtransaction.presentation.model.CategoryModel

fun CategoryDTO.toPresentation(): CategoryModel {
    return CategoryModel(id, name, MyBalanceIcons.from(iconKey))
}