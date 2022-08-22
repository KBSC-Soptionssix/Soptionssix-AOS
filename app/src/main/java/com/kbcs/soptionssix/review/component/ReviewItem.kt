package com.kbcs.soptionssix.review.component

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.review.Review

@Composable
fun ReviewItem(review: Review) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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
                Modifier.fillMaxWidth().padding(16.dp)
            ) {
                ReviewInformation(
                    title = review.title,
                    time = review.time,
                    address = review.address
                )
                Spacer(Modifier.size(4.dp))
                ReviewContent(content = review.content)
                Spacer(Modifier.size(16.dp))
                StoreInformation(storeName = review.storeName, reviewImages = review.reviewImages)
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
    storeName: String,
    reviewImages: List<String>,
    imageHeight: Dp = 98.dp
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = storeName,
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.dark_green)
            )
            Image(painter = painterResource(id = R.drawable.ic_temp_right), contentDescription = "")
        }
        Spacer(Modifier.size(4.dp))
        if (reviewImages.size == 1) {
            AsyncImage(
                model = R.drawable.ic_launcher_foreground,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            )
        } else if (reviewImages.size == 2) {
            Row(Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = R.drawable.ic_launcher_foreground,
                    contentDescription = "",
                    modifier =
                    Modifier
                        .height(imageHeight)
                        .weight(1f)
                        .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                        .background(Color.Gray)
                )
                Spacer(Modifier.size(4.dp))
                AsyncImage(
                    model = R.drawable.ic_launcher_foreground,
                    contentDescription = "",
                    modifier = Modifier
                        .height(imageHeight)
                        .weight(1f)
                        .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                        .background(Color.Gray)
                )
            }
        }
    }
}

@Preview
@Composable
fun ReviewPreview() {
    ReviewItem(
        Review(
            title = "맑은비",
            time = "5분 전",
            address = "백현동",
            content = "첫번째 방문에 너무 득템했는데\n" +
                "오늘 두번쨰 방문인데 후기를 안남길 수가 없어요\n" +
                "참깨, 무화과, 갈릭바게트 베이글 전부 다 존맛이고 크림치즈 서비스스스스스스\n" +
                "이거까지 보이나요???",
            storeName = "몽실 베이커리"
        )
    )
}
