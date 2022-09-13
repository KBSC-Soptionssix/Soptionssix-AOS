package com.kbcs.soptionssix.util.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.exchangetab.Receipt
import com.kbsc.data.dto.ReviewDto
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@Composable
fun ExchangeItem(
    receipt: Receipt,
    review: ReviewDto?,
    goExchangeDetail: (String) -> Unit,
    goWriteReview: (String?) -> Unit,
    goReadReview: (String?) -> Unit,
    goStoreDetail: (String) -> Unit
) {
    val currentTime = Calendar.getInstance().timeInMillis / 1000L
    val state: Int =
        if (receipt.review != null) 3
        else if (currentTime > receipt.pickUpDate) 2
        else 1
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
        1 -> fun() { goExchangeDetail(receipt.id) }
        2 -> fun() { goWriteReview(review?.id) }
        else -> fun() { goReadReview(review?.id) }
    }
    Surface(
        modifier = Modifier.background(colorResource(id = R.color.white)),
        elevation = 4.dp
    ) {
        Column {
            ExchangeDate(
                exchangeDate = receipt.exchangeDate
            )
            ExchangeStore(
                storeId = receipt.storeId,
                storeName = receipt.storeName,
                goStoreDetail = goStoreDetail
            )
            ExchangeFood(
                foodName = receipt.productName,
                foodCount = receipt.productCount
            )
            ExchangePaymentHistory(
                totalPrice = receipt.totalProductPrice
            )
            ExchangeTime(
                state = state,
                pickUpDate = receipt.pickUpDate
            )
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

@SuppressLint("SimpleDateFormat")
@Composable
private fun ExchangeDate(
    exchangeDate: Long
) {
    val date = Date()
    date.time = exchangeDate * 1000L
    val formatter = SimpleDateFormat("yyyy.MM.dd")
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
                text = formatter.format(date),
                style = MaterialTheme.typography.h4,
                color = colorResource(id = R.color.black)
            )
        }
    )
}

@Composable
private fun ExchangeStore(
    storeId: String,
    storeName: String,
    goStoreDetail: (String) -> Unit
) {
    ExchangeItemRowFrame(
        prefixIcon = R.drawable.ic_shop,
        prefixContent = { modifier ->
            Text(
                modifier = modifier,
                text = storeName,
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.dark_green)
            )
        },
        postfixContent = {
            Image(
                modifier = Modifier.clickable { goStoreDetail(storeId) },
                painter = painterResource(id = R.drawable.ic_rightmore),
                contentDescription = ""
            )
        }
    )
}

@Composable
private fun ExchangeFood(
    foodName: String,
    foodCount: Int
) {
    ExchangeItemRowFrame(
        prefixIcon = R.drawable.ic_soup,
        prefixContent = { modifier ->
            Text(
                modifier = modifier,
                text = foodName,
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.black)
            )
        },
        postfixContent = {
            Text(
                text = "${foodCount}개",
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.black)
            )
        }
    )
}

@Composable
private fun ExchangePaymentHistory(
    totalPrice: Int
) {
    val decFormatter = DecimalFormat("#,###")
    ExchangeItemRowFrame(
        prefixIcon = R.drawable.ic_paycard,
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
                text = decFormatter.format(totalPrice) + '원',
                style = MaterialTheme.typography.body2,
                color = colorResource(id = R.color.black)
            )
        }
    )
}

@SuppressLint("SimpleDateFormat")
@Composable
private fun ExchangeTime(
    state: Int,
    pickUpDate: Long
) {
    val darkGreenColor = colorResource(id = R.color.dark_green)
    val blackColor = colorResource(id = R.color.black)
    val date = Date()
    date.time = pickUpDate * 1000L
    val dayFormatter = SimpleDateFormat("EE")
    val dayDividerFormatter = SimpleDateFormat("aa")
    val formatter =
        SimpleDateFormat(
            "yyyy년 MM월 dd일 (${DayFormatter(dayFormatter.format(date))}) ${
            DayDividerFormatter(
                dayDividerFormatter.format(date)
            )
            } hh시 mm분 "
        )
    ExchangeItemRowFrame(
        prefixIcon = R.drawable.ic_clock,
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
                        if (state == 1) append(formatter.format(date)) else append("교환 완료")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = blackColor,
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        if (state == 1) append("까지 갈게요!")
                    }
                }
            )
        }
    )
}

private fun DayFormatter(day: String): String {
    return when (day) {
        "Mon" -> "월"
        "Tue" -> "화"
        "Wed" -> "수"
        "Thu" -> "목"
        "Fri" -> "금"
        "Sat" -> "토"
        else -> "일"
    }
}

private fun DayDividerFormatter(divider: String): String {
    return when (divider) {
        "AM" -> "오전"
        else -> "오후"
    }
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
