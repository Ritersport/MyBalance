package com.ritesrport.core.designsystem

import androidx.compose.ui.graphics.vector.ImageVector
import com.ritesrport.core.designsystem.icons.Bus
import com.ritesrport.core.designsystem.icons.Check
import com.ritesrport.core.designsystem.icons.ChevronRight
import com.ritesrport.core.designsystem.icons.Close
import com.ritesrport.core.designsystem.icons.DateRange
import com.ritesrport.core.designsystem.icons.ShoppingCart
import kotlin.collections.List as ____KtList

object MyBalanceIcons

private var __AllIcons: ____KtList<ImageVector>? = null

val MyBalanceIcons.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Check, ChevronRight, Close, DateRange, ShoppingCart, Bus)
    return __AllIcons!!
  }
