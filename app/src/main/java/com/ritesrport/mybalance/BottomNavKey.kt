package com.ritesrport.mybalance

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface BottomNavKey : NavKey {
    val label: String
    val icon: ImageVector

    @Serializable
    data object TransactionsList : BottomNavKey {
        override val label = "Transactions"
        override val icon: ImageVector = Icons.Outlined.Receipt
    }

    companion object {
        val items = listOf(TransactionsList)

        val stateSaver = Saver<BottomNavKey, String>(
            save = { it::class.qualifiedName },
            restore = { qualifiedClass ->
                items.firstOrNull { it::class.qualifiedName == qualifiedClass } ?: TransactionsList
            })
    }
}
