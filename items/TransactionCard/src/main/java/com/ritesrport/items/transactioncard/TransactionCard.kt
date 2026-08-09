package com.ritesrport.items.transactioncard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesrport.core.designsystem.theme.MyBalanceTheme

@Composable
fun TransactionCard(
    transaction: TransactionModel,
    transactionInterface: TransactionInterface,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { transactionInterface.onTransactionCardClick(transaction.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MyBalanceTheme.colors.surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(MyBalanceTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                transaction.icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(44.dp)
                    .background(transaction.iconColor, CircleShape)
                    .padding(MyBalanceTheme.spacing.small)

            )

            Spacer(modifier = Modifier.width(MyBalanceTheme.spacing.medium))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.title,
                    style = MyBalanceTheme.typography.caption,
                    color = MyBalanceTheme.colors.textPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = transaction.category,
                    style = MyBalanceTheme.typography.description,
                    color = MyBalanceTheme.colors.textSecondary
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = transaction.amount,
                    style = MyBalanceTheme.typography.caption,
                    color = when (transaction.type) {
                        TransactionType.INCOME -> MyBalanceTheme.colors.income
                        TransactionType.EXPENSE -> MyBalanceTheme.colors.expense
                        TransactionType.TRANSFER -> MyBalanceTheme.colors.transfer
                    }
                )

                Spacer(modifier = Modifier.height(MyBalanceTheme.spacing.extraSmall))

                Text(
                    text = transaction.additionalInfo,
                    style = MyBalanceTheme.typography.description,
                    color = MyBalanceTheme.colors.textSecondary
                )
            }
        }
    }
}


@Preview
@Composable
fun TransactionCardPreview() {
    MyBalanceTheme(darkTheme = false) {
        val model = transactionModelExpensePreview
        val transactionInterface = transactionInterfacePreview
        TransactionCard(model, transactionInterface)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TransactionCardPreviewDark() {
    MyBalanceTheme(darkTheme = true) {
        val model = transactionModelExpensePreview
        val transactionInterface = transactionInterfacePreview
        TransactionCard(model, transactionInterface)
    }
}
