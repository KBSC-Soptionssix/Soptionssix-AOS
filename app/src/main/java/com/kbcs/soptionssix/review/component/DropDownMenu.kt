package com.kbcs.soptionssix.review.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
                    Text(text = menuText)
                }
            }
        }
    }
}

@Preview
@Composable
fun DropDownMenuPreview() {
    val previewList = mutableListOf("A", "B", "C", "D", "E")
    DropDownMenu(previewList)
}
