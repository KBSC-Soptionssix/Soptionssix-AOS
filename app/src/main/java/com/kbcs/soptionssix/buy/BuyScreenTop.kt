package com.kbcs.soptionssix.buy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.component.FoodDetail
import com.kbcs.soptionssix.util.component.InputPhoneNumber
import com.kbcs.soptionssix.util.component.InvisibleGuestToolBar
import com.kbcs.soptionssix.util.component.TotalFoodPrice
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun BuyScreenTop() {
    MaterialTheme(typography = PretendardTypography) {
        Column(
            modifier = Modifier.background(colorResource(id = R.color.view_background))
        ) {
            BuyFoodToolBar()
            Spacer(Modifier.height(8.dp))
            FoodDetail()
            Spacer(Modifier.height(8.dp))
            TotalFoodPrice()
            Spacer(Modifier.height(8.dp))
            InputPhoneNumber()
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun BuyFoodToolBar() {
    InvisibleGuestToolBar(
        prefixContent = {
            Image(
                modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
                painter = painterResource(id = R.drawable.ic_shop),
                contentDescription = ""
            )
        },
        middleContent = {
            Text(
                modifier = Modifier.padding(vertical = 13.dp),
                text = "결제하기",
                style = MaterialTheme.typography.h1
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun BuyScreenPreview() {
    MaterialTheme(typography = PretendardTypography) {
        BuyScreenTop()
    }
}