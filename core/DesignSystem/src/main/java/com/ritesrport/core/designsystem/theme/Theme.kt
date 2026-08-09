package com.ritesrport.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalBalanceColors = staticCompositionLocalOf<MyBalanceColors> {
    error("No BalanceColors provided")
}

val LocalBalanceTypography = staticCompositionLocalOf<MyBalanceTypography> {
    error("No BalanceTypography provided")
}

val LocalBalanceSpacing = staticCompositionLocalOf<MyBalanceSpacing> {
    error("No BalanceSpacing provided")
}

object MyBalanceTheme {
    val colors: MyBalanceColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBalanceColors.current

    val typography: MyBalanceTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalBalanceTypography.current

    val spacing: MyBalanceSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalBalanceSpacing.current
}

@Composable
fun MyBalanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkBalanceColors else LightBalanceColors
    
    CompositionLocalProvider(
        LocalBalanceColors provides colors,
        LocalBalanceTypography provides BalanceTypography,
        LocalBalanceSpacing provides BalanceSpacing
    ) {
        content()
    }
}
