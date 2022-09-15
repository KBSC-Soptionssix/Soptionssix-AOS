package com.kbcs.soptionssix.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.component.InvisibleGuestButton
import com.kbcs.soptionssix.util.component.InvisibleGuestToolBar
import com.kbcs.soptionssix.util.component.WriteReviewBox
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun WriteReviewScreen(
    modifier: Modifier,
    title: String,
    buttonText: String,
    writeReviewViewModel: WriteReviewViewModel,
    buttonEvent: () -> Unit,
    finish: () -> Unit
) {
    val reviewContent = writeReviewViewModel.reviewContent.collectAsState()
    val buttonState = writeReviewViewModel.buttonState.collectAsState()
    MaterialTheme(typography = PretendardTypography) {
        Column(
            modifier = modifier
        ) {
            Column(modifier = Modifier.weight(1f)) {
                WriteToolBar(
                    title = title,
                    finish = finish
                )
                WriteReviewBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .height(270.dp)
                        .wrapContentHeight()
                        .background(Color.White),
                    reviewContent = reviewContent.value,
                    writeReview = writeReviewViewModel::writeReview
                )
            }
            InvisibleGuestButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white))
                    .padding(8.dp)
                    .imePadding(),
                isClickable = buttonState.value,
                buttonText = buttonText,
                onClickEvent = buttonEvent,
                finish = finish
            )
        }
    }
}

@Composable
fun WriteToolBar(
    title: String,
    finish: () -> Unit
) {
    InvisibleGuestToolBar(
        prefixContent = {
            Image(
                modifier = Modifier
                    .clickable { finish() }
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = ""
            )
        },
        middleContent = {
            Text(
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, end = 32.dp),
                text = title,
                style = MaterialTheme.typography.h1
            )
        }
    )
}
