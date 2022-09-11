package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import java.text.DecimalFormat

@Composable
fun TotalFoodPrice(
    totalFoodPrice: Int,
    foodDiscount: Int,
    foodPrice: Int,
    foodCount: Int
) {
    val decFormatter = DecimalFormat("#,###")
    Column(
        Modifier
            .background(color = colorResource(id = R.color.white))
            .padding(16.dp)
    ) {
        Text(
            text = "총 결제 금액",
            style = MaterialTheme.typography.h1
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "반짝할인 혜택",
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "- ${decFormatter.format((foodPrice * foodDiscount / 100) * foodCount)}원",
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "결제 예정 금액",
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "${decFormatter.format(totalFoodPrice)}원",
                style = MaterialTheme.typography.h2,
                color = colorResource(id = R.color.dark_green)
            )
        }
    }
}
