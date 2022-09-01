package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun SelectArrivalTime() {
    val timeList = listOf("10분", "20분", "30분", "40분", "50분")
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
            style = MaterialTheme.typography.button,
            color = colorResource(id = R.color.dark_gray)
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(timeList) {
                TimeChip(timeText = it, isChecked = false)
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "도착 예정 시간",
            style = MaterialTheme.typography.button,
            color = colorResource(id = R.color.dark_gray)
        )
        Spacer(Modifier.height(8.dp))
        ExpectedTimeBox()
    }
}

@Composable
private fun TimeChip(
    timeText: String,
    isChecked: Boolean
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(if (isChecked) colorResource(id = R.color.dark_green) else colorResource(id = R.color.white))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(18.dp),
                color = colorResource(id = R.color.pale_gray)
            )
            .padding(horizontal = 11.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = timeText,
            style = MaterialTheme.typography.body2,
            color = if (isChecked) colorResource(id = R.color.white) else colorResource(id = R.color.light_gray)
        )
    }
}

@Composable
private fun ExpectedTimeBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.pale_yellow))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(4.dp),
                color = colorResource(id = R.color.light_yellow)
            )
            .padding(vertical = 10.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "약 3시 33분",
            style = MaterialTheme.typography.h4
        )
        Image(painter = painterResource(id = R.drawable.ic_shop), contentDescription = "")
    }
}
