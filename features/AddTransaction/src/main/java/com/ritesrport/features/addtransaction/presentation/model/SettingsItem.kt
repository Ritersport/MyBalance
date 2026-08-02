package com.ritesrport.features.addtransaction.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.DateRange

data class SettingsItem(val icon: ImageVector, val title: String, val value: String)

val settingsItemPreview = SettingsItem(MyBalanceIcons.DateRange, "Категория", "Еда и напитки")