package com.kbcs.soptionssix.write

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.component.ReviewRegisterButton
import com.kbcs.soptionssix.util.component.WriteReviewBox
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun WriteReviewScreen(
    modifier: Modifier
) {
    MaterialTheme(typography = PretendardTypography) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            WriteReviewBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .height(270.dp)
                    .wrapContentHeight()
                    .background(Color.White),
                storeName = "떡도리탕",
                menuName = "부리또"
            )
            ReviewRegisterButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white))
                    .padding(8.dp)
                    .imePadding(),
                isClickable = false
            )
        }
    }
}

@Preview
@Composable
private fun WriteReviewScreenPreview() {
    WriteReviewScreen(Modifier)
}
