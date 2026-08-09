package com.ritesrport.core.designsystem.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesrport.core.designsystem.MyBalanceIcons

val MyBalanceIcons.ShoppingCart: ImageVector
    get() {
        if (_shoppingCart != null) {
            return _shoppingCart!!
        }
        _shoppingCart = Builder(name = "ShoppingCart", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF1f1f1f)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(223.5f, 856.5f)
                quadTo(200.0f, 833.0f, 200.0f, 800.0f)
                reflectiveQuadToRelative(23.5f, -56.5f)
                quadTo(247.0f, 720.0f, 280.0f, 720.0f)
                reflectiveQuadToRelative(56.5f, 23.5f)
                quadTo(360.0f, 767.0f, 360.0f, 800.0f)
                reflectiveQuadToRelative(-23.5f, 56.5f)
                quadTo(313.0f, 880.0f, 280.0f, 880.0f)
                reflectiveQuadToRelative(-56.5f, -23.5f)
                close()
                moveTo(623.5f, 856.5f)
                quadTo(600.0f, 833.0f, 600.0f, 800.0f)
                reflectiveQuadToRelative(23.5f, -56.5f)
                quadTo(647.0f, 720.0f, 680.0f, 720.0f)
                reflectiveQuadToRelative(56.5f, 23.5f)
                quadTo(760.0f, 767.0f, 760.0f, 800.0f)
                reflectiveQuadToRelative(-23.5f, 56.5f)
                quadTo(713.0f, 880.0f, 680.0f, 880.0f)
                reflectiveQuadToRelative(-56.5f, -23.5f)
                close()
                moveTo(246.0f, 240.0f)
                lineToRelative(96.0f, 200.0f)
                horizontalLineToRelative(280.0f)
                lineToRelative(110.0f, -200.0f)
                lineTo(246.0f, 240.0f)
                close()
                moveTo(208.0f, 160.0f)
                horizontalLineToRelative(590.0f)
                quadToRelative(23.0f, 0.0f, 35.0f, 20.5f)
                reflectiveQuadToRelative(1.0f, 41.5f)
                lineTo(692.0f, 478.0f)
                quadToRelative(-11.0f, 20.0f, -29.5f, 31.0f)
                reflectiveQuadTo(622.0f, 520.0f)
                lineTo(324.0f, 520.0f)
                lineToRelative(-44.0f, 80.0f)
                horizontalLineToRelative(480.0f)
                verticalLineToRelative(80.0f)
                lineTo(280.0f, 680.0f)
                quadToRelative(-45.0f, 0.0f, -68.0f, -39.5f)
                reflectiveQuadToRelative(-2.0f, -78.5f)
                lineToRelative(54.0f, -98.0f)
                lineToRelative(-144.0f, -304.0f)
                lineTo(40.0f, 160.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(130.0f)
                lineToRelative(38.0f, 80.0f)
                close()
                moveTo(342.0f, 440.0f)
                horizontalLineToRelative(280.0f)
                horizontalLineToRelative(-280.0f)
                close()
            }
        }
        .build()
        return _shoppingCart!!
    }

private var _shoppingCart: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyBalanceIcons.ShoppingCart, contentDescription = "")
    }
}
