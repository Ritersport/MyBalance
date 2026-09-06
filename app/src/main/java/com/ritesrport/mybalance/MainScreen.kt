package com.ritesrport.mybalance

import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import com.ritesrport.features.addtransaction.presentation.AddTransactionScreen
import com.ritesrport.transactionlist.presentation.TransactionListScreen


@Composable
fun MainScreen() {
    val transactionsBackStack = rememberNavBackStack(BottomNavKey.TransactionsList)
    val addTransactionBackStack = rememberNavBackStack(BottomNavKey.AddTransaction)

    var currentKey by rememberSaveable(stateSaver = BottomNavKey.stateSaver) {
        mutableStateOf(BottomNavKey.TransactionsList)
    }
    val currentBackStack = when (currentKey) {
        BottomNavKey.TransactionsList -> transactionsBackStack
        BottomNavKey.AddTransaction -> addTransactionBackStack
    }


    Column(modifier = Modifier.fillMaxSize()) {
        NavDisplay(
            modifier = Modifier.weight(1f),
            backStack = currentBackStack,
            entryProvider = entryProvider {
                entry<BottomNavKey.TransactionsList> {
                    TransactionListScreen()
                }
                entry<BottomNavKey.AddTransaction> {
                    AddTransactionScreen({currentKey = BottomNavKey.TransactionsList})
                }
            },
            transitionSpec = { EnterTransition.None togetherWith ExitTransition.None },
            popTransitionSpec = { EnterTransition.None togetherWith ExitTransition.None },
            predictivePopTransitionSpec = { EnterTransition.None togetherWith ExitTransition.None }
        )

        if (currentKey.showBottomBar)
        {
            NavigationBar {
                BottomNavKey.items.forEach { key ->
                    val label = key.label
                    val icon = key.icon
                    NavigationBarItem(
                        selected = key == currentKey,
                        onClick = {
                            if (currentKey != key) {
                                currentKey = key
                            }
                        },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) })
                }
            }
        }


    }

    val canGoBack = currentBackStack.size > 1

    BackHandler(enabled = canGoBack) {
        currentBackStack.removeLastOrNull()
    }
}
