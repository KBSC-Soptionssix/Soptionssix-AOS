package com.kbcs.soptionssix.buy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.loading.LoadingToolBar
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun BuyFinishScreen(
    finish: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        LoadingToolBar()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(colorResource(id = R.color.view_background))
        )
        BuyFinishContent(finish = finish)
    }
}

@Composable
fun BuyFinishContent(
    finish: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {}
        Text(
            text = "결제가 완료되었습니다",
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .clickable { finish() }
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(colorResource(id = R.color.dark_gray_2))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                text = "교환증 보기",
                color = colorResource(id = R.color.white),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MaterialTheme(
        typography = PretendardTypography
    ) {
        BuyFinishScreen({})
    }
}
