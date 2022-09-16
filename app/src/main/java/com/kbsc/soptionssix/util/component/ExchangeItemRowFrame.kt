package com.kbsc.soptionssix.util.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kbsc.soptionssix.R

@Composable
fun ExchangeItemRowFrame(
    @DrawableRes prefixIcon: Int? = null,
    prefixContent: @Composable (modifier: Modifier) -> Unit = {},
    postfixContent: @Composable () -> Unit = {}
) {
    val borderColor = colorResource(id = R.color.view_background)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = Stroke.DefaultMiter
                val y = size.height - strokeWidth / 2

                drawLine(
                    color = borderColor,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (prefixIcon != null) {
            Image(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = prefixIcon),
                contentDescription = ""
            )
        }
        prefixContent(Modifier.weight(1f))
        postfixContent()
    }
}
