package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun ArrivalTimeInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .padding(16.dp)
    ) {
        Text(
            text = "도착 예정 시간",
            style = MaterialTheme.typography.h1
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "가게 방문까지 걸리는 시간",
            style = MaterialTheme.typography.h1,
            color = colorResource(id = R.color.dark_gray)
        )
        Spacer(Modifier.height(8.dp))
    }
}
