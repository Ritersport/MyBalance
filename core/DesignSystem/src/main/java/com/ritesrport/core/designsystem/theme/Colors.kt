package com.ritesrport.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class MyBalanceColors(
    val brand: Color,
    val background: Color,
    val surface: Color,
    val onSurface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val income: Color,
    val expense: Color,
    val transfer: Color,
    val categoryPalette: List<Color>,
    val isLight: Boolean
)

val LightBalanceColors = MyBalanceColors(
    brand = Color(0xFF7F3DFF),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFBFBFB),
    onSurface = Color(0xFF0D0E0F),
    textPrimary = Color(0xFF0D0E0F),
    textSecondary = Color(0xFF91919F),
    income = Color(0xFF00A86B),
    expense = Color(0xFFFD3C4A),
    transfer = Color(0xFF0077FF),
    categoryPalette = listOf(
        Color(0xFFFFEB3B), // Yellow
        Color(0xFF4CAF50), // Green
        Color(0xFF2196F3), // Blue
        Color(0xFFF44336), // Red
        Color(0xFF9C27B0), // Purple
        Color(0xFFFF9800)  // Orange
    ),
    isLight = true
)

val DarkBalanceColors = MyBalanceColors(
    brand = Color(0xFF8F57FF),
    background = Color(0xFF0D0E0F),
    surface = Color(0xFF161719),
    onSurface = Color(0xFFFFFFFF),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFF91919F),
    income = Color(0xFF00D085),
    expense = Color(0xFFFF525D),
    transfer = Color(0xFF3395FF),
    categoryPalette = listOf(
        Color(0xFFFFEB3B),
        Color(0xFF4CAF50),
        Color(0xFF2196F3),
        Color(0xFFF44336),
        Color(0xFF9C27B0),
        Color(0xFFFF9800)
    ),
    isLight = false
)
