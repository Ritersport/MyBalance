package com.ritesrport.features.addtransaction.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.ChevronRight
import com.ritesrport.features.addtransaction.presentation.model.SettingsItem
import com.ritesrport.features.addtransaction.presentation.model.settingsItemPreview

@Composable
fun SettingsRow(item: SettingsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {}
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {

        Icon(
            imageVector = item.icon, contentDescription = null, modifier = Modifier.size(28.dp)
        )

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = item.title, style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = item.value, style = MaterialTheme.typography.bodyLarge
            )
        }

        Icon(
            MyBalanceIcons.ChevronRight, contentDescription = null
        )
    }
}

@Preview
@Composable
fun SettingsRowPreview() {
    SettingsRow(settingsItemPreview)
}