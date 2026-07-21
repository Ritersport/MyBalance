package com.ritesrport.mybalance

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.ritesrport.transactionlist.presentation.TransactionListScreen


@Composable
fun MainScreen() {
    val transactionsBackStack = rememberNavBackStack(BottomNavKey.TransactionsList)

    var currentKey by rememberSaveable(stateSaver = BottomNavKey.stateSaver) {
        mutableStateOf(BottomNavKey.TransactionsList)
    }
    val currentBackStack = when (currentKey) {
        BottomNavKey.TransactionsList -> transactionsBackStack
    }


    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavKey.items.forEach { key ->
                    NavigationBarItem(
                        selected = key == currentKey,
                        onClick = {
                            if (currentKey != key) {
                                currentKey = key
                            }
                        },
                        icon = { Icon(key.icon, contentDescription = key.label) },
                        label = { Text(key.label) })
                }
            }
        }) { paddingValues ->

        NavDisplay(
            modifier = Modifier.padding(paddingValues),
            backStack = currentBackStack,
            entryProvider = entryProvider {
                entry<BottomNavKey.TransactionsList> {
                    TransactionListScreen()
                }
            }
        )
    }

    val canGoBack = currentBackStack.size > 1

    BackHandler(enabled = canGoBack) {
        currentBackStack.removeLastOrNull()
    }
}
