package com.kbsc.soptionssix.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kbsc.soptionssix.R

@Composable
fun SelectBuyMethod(
    selectPaymentIndex: Int,
    paymentList: List<String>,
    setPaymentState: (Int) -> Unit
) {
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
        Box {
            LazyVerticalGrid(
                modifier = Modifier
                    .height(206.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(paymentList) { index, payment ->
                    PaymentBox(
                        index = index,
                        paymentText = payment,
                        isChecked = index == selectPaymentIndex,
                        setPaymentState = setPaymentState
                    )
                }
            }
        }
    }
}

@Composable
private fun PaymentBox(
    index: Int,
    paymentText: String,
    isChecked: Boolean,
    setPaymentState: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { setPaymentState(index) }
            .clip(RoundedCornerShape(2.dp))
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
