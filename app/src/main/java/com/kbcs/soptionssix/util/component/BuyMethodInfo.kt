package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun BuyMethodInfo() {
    val paymentList = listOf("신용/체크카드", "휴대폰결제", "네이버페이", "카카오페이", "토스", "페이코")
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.white))
            .padding(16.dp)
    ) {
        Text(
            text = "결제수단",
            style = MaterialTheme.typography.h1
        )
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            modifier = Modifier.height(206.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(paymentList) {
                PaymentBox(paymentText = it, isChecked = false)
            }
        }
    }
}

@Composable
private fun PaymentBox(
    paymentText: String,
    isChecked: Boolean
) {
    Box(
        modifier = Modifier
            .background(
                if (isChecked) colorResource(id = R.color.dark_green)
                else colorResource(id = R.color.white)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(2.dp),
                color = if (isChecked) colorResource(id = R.color.dark_green) else colorResource(id = R.color.pale_gray)
            )
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = paymentText,
            style = MaterialTheme.typography.h2,
            color = if (isChecked) colorResource(id = R.color.white) else colorResource(id = R.color.gray),
            textAlign = TextAlign.Center
        )
    }
}
