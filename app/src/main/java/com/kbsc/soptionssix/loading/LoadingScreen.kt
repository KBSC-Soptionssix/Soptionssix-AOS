package com.kbsc.soptionssix.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbsc.soptionssix.R
import com.kbsc.soptionssix.util.theme.PretendardTypography

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LoadingToolBar()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(colorResource(id = R.color.view_background))
        )
        LoadingContent()
    }
}

@Composable
fun LoadingToolBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.buyText),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.chronometer),
            contentDescription = ""
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Text(
            text = stringResource(R.string.buyingText),
            style = MaterialTheme.typography.h3
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MaterialTheme(
        typography = PretendardTypography
    ) {
        LoadingScreen()
    }
}
