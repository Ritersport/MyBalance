package com.ritesrport.features.addtransaction.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritersport.sharedRepository.transactionRepository.dto.CategoryDTO
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.formatters.toUserString
import com.ritesrport.core.designsystem.icons.DateRange
import java.time.LocalDate

data class SettingsItem(val icon: ImageVector, val title: String, val value: String)

val settingsItemPreview = SettingsItem(MyBalanceIcons.DateRange, "Категория", "Еда и напитки")

fun CategoryDTO.toSettingsItem(): SettingsItem {
    return SettingsItem(MyBalanceIcons.from(iconKey), "Категория", name)
}

fun AccountDTO.toSettingsItem(): SettingsItem {
    return SettingsItem(MyBalanceIcons.from(iconKey), "Счёт", name)
}

fun LocalDate.toSettingsItem(): SettingsItem {
    return SettingsItem(MyBalanceIcons.DateRange, "Дата", toUserString())
}