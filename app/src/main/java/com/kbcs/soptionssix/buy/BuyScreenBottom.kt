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
import com.kbcs.soptionssix.util.component.ArrivalTimeInfo
import com.kbcs.soptionssix.util.component.BuyMethodInfo
import com.kbcs.soptionssix.util.component.IsChallengeInfo
import com.kbcs.soptionssix.util.component.ReviewRegisterButton
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun BuyScreenBottom() {
    Column(modifier = Modifier.background(colorResource(id = R.color.view_background))) {
        Spacer(Modifier.height(8.dp))
        ArrivalTimeInfo()
        Spacer(Modifier.height(8.dp))
        IsChallengeInfo()
        Spacer(Modifier.height(8.dp))
        BuyMethodInfo()
        Spacer(Modifier.height(88.dp))
        ReviewRegisterButton(
            modifier = Modifier.fillMaxWidth()
                .background(colorResource(id = R.color.white))
                .padding(8.dp),
            isClickable = false,
            buttonText = "9,000원 결제하기"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BuyScreenBottomPreview() {
    MaterialTheme(typography = PretendardTypography) {
        BuyScreenBottom()
    }
}
