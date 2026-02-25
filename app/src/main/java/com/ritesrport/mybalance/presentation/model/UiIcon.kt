package com.ritesrport.mybalance.presentation.model

import com.ritesrport.mybalance.R
import com.ritesrport.mybalance.domain.entity.IconKey

data class UiIcon(val resId: Int)

fun IconKey.toUiIcon(): UiIcon {
    return UiIcon(IconRegistry.getResId(value))
}

object IconRegistry {
    private val predefinedIcons = mapOf(
        "food" to R.drawable.dsc_2011
    )

    fun getResId(key: String): Int = predefinedIcons[key] ?: R.drawable.ic_launcher_background
}