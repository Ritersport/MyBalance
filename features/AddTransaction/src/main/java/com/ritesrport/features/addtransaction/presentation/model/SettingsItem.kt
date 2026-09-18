package com.ritesrport.features.addtransaction.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.formatters.toUserString
import com.ritesrport.core.designsystem.icons.DateRange
import java.time.LocalDate
import java.util.Calendar

data class SettingsItem(val icon: ImageVector, val title: String, val value: String)

val settingsItemPreview = SettingsItem(MyBalanceIcons.DateRange, "Категория", "Еда и напитки")

fun CategoryModel.toSettingsItem(): SettingsItem {
    return SettingsItem(icon, "Категория", name)
}

fun AccountModel.toSettingsItem(): SettingsItem {
    return SettingsItem(icon, "Счёт", name)
}

fun LocalDate.toSettingsItem(): SettingsItem {
    return SettingsItem(MyBalanceIcons.DateRange, "Дата", toUserString())
}