package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun FoodDetail() {
    Column(Modifier.background(colorResource(id = R.color.white))) {
        ExchangeItemRowFrame(
            prefixIcon = R.drawable.ic_temp_storefront,
            prefixContent = { modifier ->
                Text(
                    modifier = modifier,
                    text = "떡도리탕",
                    style = MaterialTheme.typography.body2,
                    color = colorResource(id = R.color.black)
                )
            }
        )
        Row(
            Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(3.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_temp_fire),
                    contentDescription = ""
                )
            }
            Spacer(Modifier.width(5.dp))
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        style = MaterialTheme.typography.h5,
                        text = "상큼 라임 비프 타코 2p"
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_shop),
                        contentDescription = ""
                    )
                }
                Text(
                    text = "9,000",
                    style = MaterialTheme.typography.h6,
                    color = colorResource(id = R.color.light_gray),
                    textDecoration = TextDecoration.LineThrough
                )
                Row {
                    Text(
                        text = "50%",
                        style = MaterialTheme.typography.body2,
                        color = colorResource(id = R.color.orange)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = "4,500원",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
        FoodCountWidget()
    }
}

@Composable
private fun FoodCountWidget() {
    Box(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(end = 16.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.pale_gray),
                    shape = RectangleShape
                )
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(id = R.drawable.ic_soup),
                contentDescription = ""
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = colorResource(id = R.color.pale_gray)
            )
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 7.dp, horizontal = 16.dp),
                text = "2",
                style = MaterialTheme.typography.body2
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = colorResource(id = R.color.pale_gray)
            )
            Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(id = R.drawable.ic_soup),
                contentDescription = ""
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun FoodInformationPreview() {
    MaterialTheme(typography = PretendardTypography) {
        FoodDetail()
    }
}
