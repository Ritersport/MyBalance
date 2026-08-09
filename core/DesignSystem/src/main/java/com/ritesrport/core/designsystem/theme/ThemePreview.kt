package com.ritesrport.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThemeVerificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyBalanceTheme.colors.background)
            .padding(MyBalanceTheme.spacing.medium)
    ) {
        Text(
            text = "Design System Preview",
            style = MyBalanceTheme.typography.header,
            color = MyBalanceTheme.colors.textPrimary
        )
        
        Spacer(modifier = Modifier.height(MyBalanceTheme.spacing.medium))
        
        Row {
            ColorBox("Income", MyBalanceTheme.colors.income)
            Spacer(modifier = Modifier.width(MyBalanceTheme.spacing.small))
            ColorBox("Expense", MyBalanceTheme.colors.expense)
            Spacer(modifier = Modifier.width(MyBalanceTheme.spacing.small))
            ColorBox("Transfer", MyBalanceTheme.colors.transfer)
        }
        
        Spacer(modifier = Modifier.height(MyBalanceTheme.spacing.large))
        
        Text(
            text = "Category Palette",
            style = MyBalanceTheme.typography.header,
            color = MyBalanceTheme.colors.textPrimary)
        Row(modifier = Modifier.padding(top = MyBalanceTheme.spacing.small)) {
            MyBalanceTheme.colors.categoryPalette.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(MyBalanceTheme.spacing.extraSmall)
                        .background(color, CircleShape)
                )
            }
        }
    }
}

@Composable
fun ColorBox(label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(color, CircleShape)
        )
        Text(
            text = label,
            style = MyBalanceTheme.typography.description,
            color = MyBalanceTheme.colors.textSecondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LightThemePreview() {
    MyBalanceTheme(darkTheme = false) {
        ThemeVerificationScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkThemePreview() {
    MyBalanceTheme(darkTheme = true) {
        ThemeVerificationScreen()
    }
}
