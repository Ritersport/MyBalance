package com.ritesrport.features.addtransaction.presentation.model

import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.formatters.toUserString
import com.ritesrport.core.designsystem.icons.DateRange
import com.ritesrport.items.settingsitem.SettingsItem
import java.time.LocalDate

fun CategoryDTO.toSettingsItem(): SettingsItem {
    return SettingsItem(MyBalanceIcons.from(iconKey), "Категория", name)
}

fun AccountDTO.toSettingsItem(): SettingsItem {
    return SettingsItem(MyBalanceIcons.from(iconKey), "Счёт", name)
}

fun LocalDate.toSettingsItem(): SettingsItem {
    return SettingsItem(MyBalanceIcons.DateRange, "Дата", toUserString())
}