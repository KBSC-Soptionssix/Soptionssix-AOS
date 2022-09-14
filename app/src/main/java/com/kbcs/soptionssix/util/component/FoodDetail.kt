package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kbcs.soptionssix.R
import java.text.DecimalFormat

@Composable
fun FoodDetail(
    storeName: String,
    foodImg: String?,
    foodName: String,
    foodPrice: Int,
    foodDiscount: Int,
    foodCount: Int,
    setFoodCount: (Int) -> Unit
) {
    val decFormatter = DecimalFormat("#,###")
    Column(Modifier.background(colorResource(id = R.color.white))) {
        ExchangeItemRowFrame(
            prefixIcon = R.drawable.ic_shop,
            prefixContent = { modifier ->
                Text(
                    modifier = modifier,
                    text = storeName,
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
            AsyncImage(
                model = foodImg,
                contentDescription = "",
                modifier = Modifier
                    .size(84.dp)
                    .clip(RoundedCornerShape(3.dp)),
                contentScale = ContentScale.FillBounds,
                error = painterResource(id = R.drawable.ic_x)
            )
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
                        text = foodName
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_x),
                        contentDescription = ""
                    )
                }
                Text(
                    text = decFormatter.format(foodPrice),
                    style = MaterialTheme.typography.h6,
                    color = colorResource(id = R.color.light_gray),
                    textDecoration = TextDecoration.LineThrough
                )
                Row {
                    Text(
                        text = "$foodDiscount%",
                        style = MaterialTheme.typography.body2,
                        color = colorResource(id = R.color.orange)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = "${decFormatter.format(foodPrice - (foodPrice * foodDiscount / 100))}원",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
        FoodCountWidget(
            foodCount = foodCount,
            setFoodCount = setFoodCount
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun FoodCountWidget(
    foodCount: Int,
    setFoodCount: (Int) -> Unit
) {
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
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { setFoodCount(-1) }
                    .padding(horizontal = 6.dp),
                painter = painterResource(id = R.drawable.ic_minus),
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
                text = "$foodCount",
                style = MaterialTheme.typography.body2
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = colorResource(id = R.color.pale_gray)
            )
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { setFoodCount(1) }
                    .padding(horizontal = 6.dp),
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = ""
            )
        }
    }
}
