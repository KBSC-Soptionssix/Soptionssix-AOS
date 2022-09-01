package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun ExchangeItem(state: Int) {
    val buttonText = when (state) {
        1 -> stringResource(id = R.string.exchangeTabShowExchange)
        2 -> stringResource(id = R.string.exchangeTabWriteReview)
        else -> stringResource(id = R.string.exchangeTabShowReview)
    }
    val buttonColor = when (state) {
        1 -> colorResource(id = R.color.dark_gray_2)
        else -> colorResource(id = R.color.orange)
    }
    val clickEvent: () -> Unit = when (state) {
        1 -> fun() { println("1") }
        2 -> fun() { println("2") }
        else -> fun() { println("3") }
    }
    Surface(
        modifier = Modifier.background(colorResource(id = R.color.white)),
        elevation = 4.dp
    ) {
        Column {
            ExchangeDate()
            ExchangeStore()
            ExchangeFood()
            ExchangePaymentHistory()
            ExchangeTime()
            ExchangeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white))
                    .padding(8.dp),
                backgroundColor = buttonColor,
                buttonText = buttonText,
                clickEvent = { clickEvent() }
            )
        }
    }
}

@Composable
private fun ExchangeDate() {
    ExchangeItemRowFrame(
        prefixContent = { modifier ->
            Text(
                modifier = modifier,
                text = "반짝할인 교환증",
                style = MaterialTheme.typography.h2,
                color = colorResource(id = R.color.black)
            )
        },
        postfixContent = {
            Text(
                text = "2022.08.11",
                style = MaterialTheme.typography.h4,
                color = colorResource(id = R.color.black)
            )
        }
    )
}

@Composable
private fun ExchangeStore() {
    ExchangeItemRowFrame(
        prefixIcon = R.drawable.ic_temp_storefront,
        prefixContent = { modifier ->
            Text(
                modifier = modifier,
                text = "멕시칸인더보울 명동점",
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.dark_green)
            )
        },
        postfixContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_temp_right),
                contentDescription = ""
            )
        }
    )
}

@Composable
private fun ExchangeFood() {
    ExchangeItemRowFrame(
        prefixIcon = R.drawable.ic_temp_location_on,
        prefixContent = { modifier ->
            Text(
                modifier = modifier,
                text = "멕시칸 치킨 부리또",
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.black)
            )
        },
        postfixContent = {
            Text(
                text = "2개",
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.black)
            )
        }
    )
}

@Composable
private fun ExchangePaymentHistory() {
    ExchangeItemRowFrame(
        prefixIcon = R.drawable.ic_temp_location_on,
        prefixContent = { modifier ->
            Text(
                modifier = modifier,
                text = "카드 결제 완료",
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.black)
            )
        },
        postfixContent = {
            Text(
                text = "9,000원",
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.black)
            )
        }
    )
}

@Composable
private fun ExchangeTime() {
    val darkGreenColor = colorResource(id = R.color.dark_green)
    val blackColor = colorResource(id = R.color.black)
    ExchangeItemRowFrame(
        prefixIcon = R.drawable.ic_temp_location_on,
        prefixContent = {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = darkGreenColor,
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append("2022년 8월 11일 (목) 오후 2시 17분 ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = blackColor,
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append("까지 갈게요!")
                    }
                }
            )
        }
    )
}

@Composable
private fun ExchangeButton(
    modifier: Modifier,
    backgroundColor: Color,
    buttonText: String,
    clickEvent: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .clickable { clickEvent() }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 16.dp, bottom = 13.dp),
            text = buttonText,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ExchangeItemPreview() {
    MaterialTheme(typography = PretendardTypography) {
        Column {
            ExchangeItem(1)
            Spacer(modifier = Modifier.height(24.dp))
            ExchangeItem(2)
            Spacer(modifier = Modifier.height(24.dp))
            ExchangeItem(3)
        }
    }
}
