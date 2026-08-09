package com.ritesrport.transactionlist.presentation.mappers

import androidx.compose.ui.graphics.vector.ImageVector
import com.ritesrport.core.designsystem.MyBalanceIcons
import com.ritesrport.core.designsystem.icons.Bus
import com.ritesrport.core.model.IconKey

object Icons {

    fun from(key: IconKey): ImageVector =
        when (key) {
            IconKey.Bus -> MyBalanceIcons.Bus
        }
}