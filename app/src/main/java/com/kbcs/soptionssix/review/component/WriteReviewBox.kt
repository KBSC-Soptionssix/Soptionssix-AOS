package com.kbcs.soptionssix.review.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kbcs.soptionssix.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WriteReviewBox(isFocus: MutableState<Boolean>) {
    Column(Modifier.background(Color.White)) {
        StoreInformation(mutableListOf("A", "B", "C", "D", "E"))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .size(1.dp)
        )
        WriteReviewTextField(isFocus)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WriteReviewTextField(isFocus: MutableState<Boolean>) {
    val density = LocalDensity.current
    var reviewText by remember { mutableStateOf("") }
    var maxHeight by remember { mutableStateOf(120.dp) }
    val offsetAnimation: Dp by animateDpAsState(
        if (isFocus.value) maxHeight + 30.dp else 50.dp,
        tween(durationMillis = 500)
    )
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val maxChar = 301
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .bringIntoViewRequester(bringIntoViewRequester)
    ) {
        BasicTextField(
            value = reviewText,
            onValueChange = { if (it.length < maxChar) reviewText = it },
            textStyle = TextStyle(
                color = colorResource(id = R.color.black)
            ),
            onTextLayout = { textLayoutResult ->
                with(density) {
                    if (textLayoutResult.lastBaseline.toDp() > 120.dp) {
                        maxHeight = textLayoutResult.lastBaseline.toDp() + 20.dp
                    } else if (textLayoutResult.lastBaseline.toDp() < 120.dp) maxHeight = 120.dp
                }
            },
            modifier = Modifier
                .focusable()
                .fillMaxWidth()
                .wrapContentHeight()
                .height(offsetAnimation)
                .padding(16.dp)
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        isFocus.value = true
                        coroutineScope.launch {
                            delay(200)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            maxLines = if (isFocus.value) 1 else Int.MAX_VALUE,
            cursorBrush = SolidColor(colorResource(id = R.color.black))
        ) { innerTextField ->
            if (reviewText.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.review_empty),
                    color = Color.Gray
                )
            }
            innerTextField()
        }
        if (isFocus.value) {
            ExpandingElements(reviewText = reviewText)
        }
    }
}

@Composable
private fun ExpandingElements(reviewText: String) {
    Text(
        modifier = Modifier.padding(start = 20.dp),
        text = "${reviewText.length} / 300"
    )
    Spacer(modifier = Modifier.size(4.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, bottom = 8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(vertical = 6.dp, horizontal = 14.dp),
            text = stringResource(id = R.string.registration)
        )
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
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.size(22.dp))
        Image(painter = painterResource(id = boxIcon), contentDescription = "")
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = boxText)
        if (isDropDownMenu) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                DropDownMenu(menuItem = menuItem)
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun WriteReviewBoxPreview() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Gray)
//    ) {
//        WriteReviewBox()
//    }
// }
