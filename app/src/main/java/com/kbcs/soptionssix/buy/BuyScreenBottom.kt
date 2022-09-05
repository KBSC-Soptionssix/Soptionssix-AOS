package com.kbcs.soptionssix.buy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.component.InvisibleGuestButton
import com.kbcs.soptionssix.util.component.IsChallengeCheck
import com.kbcs.soptionssix.util.component.SelectArrivalTime
import com.kbcs.soptionssix.util.component.SelectBuyMethod
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun BuyScreenBottom() {
    MaterialTheme(typography = PretendardTypography) {
        Column(modifier = Modifier.background(colorResource(id = R.color.view_background))) {
            Spacer(Modifier.height(8.dp))
            SelectArrivalTime()
            Spacer(Modifier.height(8.dp))
            IsChallengeCheck()
            Spacer(Modifier.height(8.dp))
            SelectBuyMethod()
            InvisibleGuestButton(
                modifier = Modifier.fillMaxWidth()
                    .background(colorResource(id = R.color.white))
                    .padding(8.dp),
                isClickable = false,
                buttonText = "9,000원 결제하기",
                onClickEvent = { }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BuyScreenBottomPreview() {
    MaterialTheme(typography = PretendardTypography) {
        BuyScreenBottom()
    }
}
