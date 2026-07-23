package com.ritesrport.features.addtransaction.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.ritesrport.core.designsystem.icons.date_range

data class SettingsItem(val icon: ImageVector, val title: String, val value: String)

val settingsItemPreview = SettingsItem( date_range, "Категория", "Еда и напитки")