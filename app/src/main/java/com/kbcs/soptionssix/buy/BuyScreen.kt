package com.kbcs.soptionssix.buy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.component.FoodInformation
import com.kbcs.soptionssix.util.component.FoodPriceBox
import com.kbcs.soptionssix.util.component.PhoneNumberInfo
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun BuyScreenTop() {
    Column(
        modifier = Modifier.background(colorResource(id = R.color.view_background))
    ) {
        Spacer(Modifier.height(8.dp))
        FoodInformation()
        Spacer(Modifier.height(8.dp))
        FoodPriceBox()
        Spacer(Modifier.height(8.dp))
        PhoneNumberInfo()
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun BuyScreenPreview() {
    MaterialTheme(typography = PretendardTypography) {
        BuyScreenTop()
    }
}
