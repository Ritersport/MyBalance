package com.ritesrport.transactionlist.presentation.mappers

import androidx.annotation.DrawableRes
import com.ritesrport.core.model.IconKey
import com.ritesrport.transactionlist.R

object Icons {

    @DrawableRes
    fun from(key: IconKey): Int =
        when (key) {
            IconKey.Bus -> R.drawable.category_icon_bus
        }
}