package com.kbsc.soptionssix.buy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kbsc.soptionssix.R
import com.kbsc.soptionssix.util.component.FoodDetail
import com.kbsc.soptionssix.util.component.InputPhoneNumber
import com.kbsc.soptionssix.util.component.InvisibleGuestToolBar
import com.kbsc.soptionssix.util.component.TotalFoodPrice
import com.kbsc.soptionssix.util.theme.PretendardTypography

@Composable
fun BuyScreenTop(
    buyViewModel: BuyViewModel,
    finish: () -> Unit
) {
    val buyUiState = buyViewModel.uiState.collectAsState()
    val totalFoodPrice = buyViewModel.totalPrice.collectAsState()
    MaterialTheme(typography = PretendardTypography) {
        Column(
            modifier = Modifier.background(colorResource(id = R.color.view_background))
        ) {
            BuyFoodToolBar(finish = finish)
            Spacer(Modifier.height(8.dp))
            FoodDetail(
                storeName = buyUiState.value.storeName,
                foodImg = buyUiState.value.foodImg,
                foodName = buyUiState.value.foodName,
                foodPrice = buyUiState.value.foodPrice,
                foodDiscount = buyUiState.value.foodDiscount,
                foodCount = buyUiState.value.foodCount,
                setFoodCount = buyViewModel::setFoodCount
            )
            Spacer(Modifier.height(8.dp))
            TotalFoodPrice(
                totalFoodPrice = totalFoodPrice.value,
                foodPrice = buyUiState.value.foodPrice,
                foodDiscount = buyUiState.value.foodDiscount,
                foodCount = buyUiState.value.foodCount
            )
            Spacer(Modifier.height(8.dp))
            InputPhoneNumber(
                phoneNumber = buyUiState.value.userPhoneNumber,
                writePhoneNumber = buyViewModel::writePhoneNumber
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun BuyFoodToolBar(finish: () -> Unit) {
    InvisibleGuestToolBar(
        prefixContent = {
            Image(
                modifier = Modifier
                    .clickable { finish() }
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = ""
            )
        },
        middleContent = {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp, end = 32.dp),
                text = stringResource(R.string.buyText),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center
            )
        }
    )
}
