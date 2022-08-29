package com.kbcs.soptionssix.exchangetab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.component.ExchangeItem
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun ExchangeTabScreen() {
    MaterialTheme(typography = PretendardTypography) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.view_background))
        ) {
            item {
                ExchangeItem(state = 1)
                Spacer(modifier = Modifier.height(24.dp))
                ExchangeItem(state = 2)
                Spacer(modifier = Modifier.height(24.dp))
                ExchangeItem(state = 3)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExchangeTabScreenPreview() {
    ExchangeTabScreen()
}
