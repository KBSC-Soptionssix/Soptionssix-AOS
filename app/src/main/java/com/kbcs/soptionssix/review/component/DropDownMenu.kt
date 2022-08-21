package com.kbcs.soptionssix.review.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun DropDownMenu(
    menuItem: List<String>
) {
    var isExpanded by remember { mutableStateOf(false) }
    Box() {
        Image(
            modifier = Modifier
                .clickable { isExpanded = true },
            painter = painterResource(id = R.drawable.ic_temp_expand_more),
            contentDescription = ""
        )

        DropdownMenu(
            modifier = Modifier,
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            menuItem.forEach { menuText ->
                DropdownMenuItem(
                    onClick = { isExpanded = false }
                ) {
                    Row(
                        modifier = Modifier.width(200.dp)
                    ) {
                        Text(
                            modifier = Modifier.width(150.dp),
                            text = menuText,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        DonationChip()
                    }
                }
            }
        }
    }
}

@Composable
fun DonationChip() {
    Box(
        Modifier
            .clip(shape = RoundedCornerShape(2.dp))
            .background(Color.LightGray),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "기부")
            Image(
                painter = painterResource(id = R.drawable.ic_temp_storefront),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun DropDownMenuPreview() {
    val previewList = mutableListOf("A", "B", "C", "D", "E")
    DropDownMenu(previewList)
}
