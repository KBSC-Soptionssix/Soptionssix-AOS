package com.kbcs.soptionssix.util.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.write.WriteReviewUiState

@Composable
fun WriteReviewBox(
    modifier: Modifier,
    reviewContent: WriteReviewUiState,
    writeReview: (String) -> Unit
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
                boxText = reviewContent.storeName
            )
            Spacer(modifier = Modifier.height(18.dp))
            CommonInformationBox(
                boxIcon = R.drawable.ic_soup,
                boxText = reviewContent.foodName
            )
            WriteReviewTextField(
                reviewText = reviewContent.reviewText,
                writeReview = writeReview,
                isRead = reviewContent.isRead
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WriteReviewTextField(
    reviewText: String,
    isRead: Boolean,
    writeReview: (String) -> Unit
) {
    val maxChar = 301
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 14.dp)
    ) {
        BasicTextField(
            value = reviewText,
            onValueChange = { if (it.length < maxChar) writeReview(it) },
            textStyle = TextStyle(
                color = colorResource(id = R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp),
            cursorBrush = SolidColor(colorResource(id = R.color.black)),
            readOnly = isRead
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
