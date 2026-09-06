package com.ritesrport.mybalance

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavKey(val showBottomBar: Boolean) : NavKey {
    abstract val label: String
    abstract val icon: ImageVector

    @Serializable
    data object TransactionsList : BottomNavKey(true) {
        override val label = "Transactions"
        override val icon: ImageVector = Icons.Outlined.Receipt
    }

    @Serializable
    data object AddTransaction : BottomNavKey(false) {
        override val label: String
            get() = "Add transaction"
        override val icon: ImageVector
            get() = Icons.Default.Add
    }


    companion object {
        val items get() = listOf(TransactionsList, AddTransaction)

        val stateSaver = Saver<BottomNavKey, String>(
            save = { it::class.qualifiedName },
            restore = { qualifiedClass ->
                items.firstOrNull { it::class.qualifiedName == qualifiedClass } ?: TransactionsList
            })
    }
}
