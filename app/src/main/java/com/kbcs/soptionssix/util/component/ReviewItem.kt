package com.kbcs.soptionssix.util.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.review.Review

@Composable
fun ReviewItem(
    review: Review,
    goStoreDetail: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = 20.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ReviewInformation(
                    title = review.userName,
                    time = review.createReviewTime,
                    address = review.userRegion
                )
                Spacer(Modifier.size(4.dp))
                ReviewContent(content = review.reviewContent)
                Spacer(Modifier.size(16.dp))
                StoreInformation(
                    storeId = review.storeId,
                    storeName = review.storeName,
                    reviewImages = review.photos,
                    goStoreDetail = goStoreDetail
                )
            }
        }
    }
}

@Composable
private fun ReviewInformation(
    title: String,
    time: String,
    address: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            color = colorResource(id = R.color.dark_gray_2)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = time,
                style = MaterialTheme.typography.body1,
                color = colorResource(id = R.color.dark_gray)
            )
            Spacer(Modifier.size(4.dp))
            Box(
                Modifier
                    .size(2.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = address,
                style = MaterialTheme.typography.body1,
                color = colorResource(id = R.color.dark_gray)
            )
        }
    }
}

@Composable
private fun ReviewContent(content: String) {
    ExpandableText(
        text = content
    )
}

const val DEFAULT_MINIMUM_TEXT_LINE = 3

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontStyle: FontStyle? = null,
    text: String,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = "... 더보기",
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.W500),
    showLessText: String = "",
    showLessStyle: SpanStyle = showMoreStyle,
    textAlign: TextAlign? = null
) {
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .clickable(clickable) {
                isExpanded = !isExpanded
            }
            .then(modifier)
    ) {
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .animateContentSize(),
            text = buildAnnotatedString {
                if (clickable) {
                    if (isExpanded) {
                        append(text)
                        withStyle(style = showLessStyle) { append(showLessText) }
                    } else {
                        val adjustText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                        append(adjustText)
                        withStyle(style = showMoreStyle) { append(showMoreText) }
                    }
                } else {
                    append(text)
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            fontStyle = fontStyle,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    clickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                }
            },
            style = style,
            textAlign = textAlign,
            color = colorResource(id = R.color.dark_gray_2)
        )
    }
}

@Composable
private fun StoreInformation(
    storeId: String,
    storeName: String,
    reviewImages: List<String>?,
    imgHeight: Dp = 100.dp,
    goStoreDetail: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = storeName,
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.yellow)
            )
            Image(
                modifier = Modifier.clickable { goStoreDetail(storeId) },
                painter = painterResource(id = R.drawable.ic_rightmore),
                contentDescription = ""
            )
        }
        if (reviewImages?.size == 1) {
            Spacer(Modifier.size(16.dp))
            AsyncImage(
                model = reviewImages[0],
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray),
                contentScale = ContentScale.FillWidth
            )
        } else if (reviewImages?.size == 2) {
            Spacer(Modifier.size(16.dp))
            Row(Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = reviewImages[0],
                    contentDescription = "",
                    modifier =
                    Modifier
                        .weight(1f)
                        .height(imgHeight)
                        .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                        .background(Color.Gray),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(Modifier.size(4.dp))
                AsyncImage(
                    model = reviewImages[1],
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1f)
                        .height(imgHeight)
                        .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                        .background(Color.Gray),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}
