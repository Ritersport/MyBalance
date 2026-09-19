package com.ritesrport.items.settingsitem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.ChevronRight
import com.ritesrport.core.designsystem.theme.MyBalanceTheme

@Composable
fun SettingsRow(
    item: SettingsItem,
    settingsInterface: SettingsInterface,
) {
    Row(
        modifier = Modifier
            .clickable { settingsInterface.onSettingsItemClick() }
            .fillMaxWidth()
            .background(MyBalanceTheme.colors.surface)
            .padding(MyBalanceTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically) {

        Icon(
            imageVector = item.icon,
            tint = MyBalanceTheme.colors.textPrimary,
            contentDescription = null,
            modifier = Modifier.size(MyBalanceTheme.spacing.extraLarge)
        )

        Spacer(Modifier.width(MyBalanceTheme.spacing.medium))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = item.title,
                style = MyBalanceTheme.typography.description,
                color = MyBalanceTheme.colors.textPrimary
            )

            Text(
                text = item.value,
                style = MyBalanceTheme.typography.caption,
                color = MyBalanceTheme.colors.textPrimary
            )
        }

        Icon(
            MyBalanceIcons.ChevronRight,
            contentDescription = null,
            tint = MyBalanceTheme.colors.textPrimary
        )
    }
}

@Preview
@Composable
fun SettingsRowPreview() {
    MyBalanceTheme {
        SettingsRow(settingsItemPreview, settingsInterfacePreview)
    }
}

@Preview
@Composable
fun SettingsRowPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        SettingsRow(settingsItemPreview, settingsInterfacePreview)
    }
}