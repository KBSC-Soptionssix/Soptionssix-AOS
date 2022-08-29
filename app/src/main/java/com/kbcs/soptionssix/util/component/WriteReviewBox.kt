package com.kbcs.soptionssix.util.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun WriteReviewBox(
    modifier: Modifier,
    storeName: String,
    menuName: String
) {
    Surface(
        elevation = 10.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.height(270.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CommonInformationBox(
                boxIcon = R.drawable.ic_shop,
                boxText = storeName
            )
            Spacer(modifier = Modifier.height(18.dp))
            CommonInformationBox(
                boxIcon = R.drawable.ic_soup,
                boxText = menuName
            )
            Spacer(modifier = Modifier.height(14.dp))
            WriteReviewTextField()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WriteReviewTextField() {
    var reviewText by remember { mutableStateOf("") }
    val maxChar = 301
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp)
    ) {
        BasicTextField(
            value = reviewText,
            onValueChange = { if (it.length < maxChar) reviewText = it },
            textStyle = TextStyle(
                color = colorResource(id = R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp),
            cursorBrush = SolidColor(colorResource(id = R.color.black))
        ) { innerTextField ->
            if (reviewText.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.reviewEmpty),
                    color = colorResource(id = R.color.light_gray),
                    style = MaterialTheme.typography.subtitle2
                )
            }
            innerTextField()
        }
    }
}

@Composable
private fun CommonInformationBox(
    @DrawableRes boxIcon: Int,
    boxText: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.size(16.dp))
        Image(painter = painterResource(id = boxIcon), contentDescription = "")
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = boxText,
            style = MaterialTheme.typography.body2,
            color = colorResource(id = R.color.black)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WriteReviewBoxPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        WriteReviewBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            storeName = "맘스터치",
            menuName = "부리또"
        )
    }
}
