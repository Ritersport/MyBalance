package com.ritesrport.features.addtransaction.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritersport.sharedRepository.transactionRepository.dto.AccountDTO
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.Close
import com.ritesrport.core.designsystem.theme.MyBalanceTheme
import com.ritesrport.core.model.IconKey
import com.ritesrport.features.addtransaction.presentation.model.toSettingsItem
import com.ritesrport.items.settingsitem.SettingsInterface
import com.ritesrport.items.settingsitem.SettingsRow

@Composable
fun SelectAccountScreen(
    title: String,
    accounts: List<AccountDTO>,
    onAccountSelected: (AccountDTO) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MyBalanceTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .background(MyBalanceTheme.colors.surface)
                .height(56.dp)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    MyBalanceIcons.Close,
                    contentDescription = "Назад",
                    tint = MyBalanceTheme.colors.brand
                )
            }

            Text(
                text = title,
                style = MyBalanceTheme.typography.header,
                color = MyBalanceTheme.colors.textPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(accounts) { account ->
                SettingsRow(
                    item = account.toSettingsItem(),
                    settingsInterface = object : SettingsInterface {
                        override fun onSettingsItemClick() {
                            onAccountSelected(account)
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview
@Composable
private fun SelectAccountScreenPreview() {
    MyBalanceTheme {
        SelectAccountScreen(
            title = "Счет списания",
            accounts = listOf(
                AccountDTO(id = 1, name = "Основной счет", iconKey = IconKey.Bus, balance = 15000.0),
                AccountDTO(id = 2, name = "Сберегательный", iconKey = IconKey.Bus, balance = 50000.0),
                AccountDTO(id = 3, name = "Наличные", iconKey = IconKey.Bus, balance = 2300.0)
            ),
            onAccountSelected = {},
            onBack = {}
        )
    }
}
