package com.kbcs.soptionssix.review.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kbcs.soptionssix.R

@Composable
fun WriteReviewBox() {
    Column {
        StoreInformation(mutableListOf("A", "B", "C", "D", "E"))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .size(1.dp)
        )
        WriteReviewTextField()
    }
}

@Composable
private fun WriteReviewTextField(
    reviewText: String = ""
) {
    val maxChar = 300
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        BasicTextField(
            value = reviewText.ifEmpty { stringResource(id = R.string.review_empty) },
            onValueChange = {},
            textStyle = TextStyle(
                color = colorResource(id = R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(if (reviewText.isNotEmpty()) 170.dp else 50.dp)
                .padding(16.dp)
                .animateContentSize(),
            cursorBrush = SolidColor(colorResource(id = R.color.black))
        )
        if (reviewText.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(start = 19.dp),
                text = "${reviewText.length} / 300"
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.registration),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun StoreInformation(menuItem: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            CommonBox(
                boxIcon = R.drawable.ic_temp_location_on,
                boxText = "광진구 능동"
            )
        }
        Text(
            text = "|",
            fontSize = 24.sp
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            CommonBox(
                boxIcon = R.drawable.ic_temp_storefront,
                boxText = stringResource(id = R.string.store),
                isDropDownMenu = true,
                menuItem = menuItem
            )
        }
    }
}

@Composable
private fun CommonBox(
    @DrawableRes boxIcon: Int,
    boxText: String,
    isDropDownMenu: Boolean = false,
    menuItem: List<String> = emptyList()
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.size(22.dp))
            Image(painter = painterResource(id = boxIcon), contentDescription = "")
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = boxText)
            if (isDropDownMenu) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                    ,
                    contentAlignment = Alignment.CenterEnd
                ) {
                    DropDownMenu(menuItem = menuItem)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WriteReviewBoxPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        WriteReviewBox()
    }
}
