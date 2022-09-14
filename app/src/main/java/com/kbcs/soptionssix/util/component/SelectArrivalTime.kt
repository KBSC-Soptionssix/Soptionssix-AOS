package com.kbcs.soptionssix.util.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun SelectPickUpTime(
    pickUpTimeList: List<String>,
    selectPickUpTimeIndex: Int,
    pickUpTime: Long,
    setPickUpTime: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.buyVisitedTime),
            style = MaterialTheme.typography.h1
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.buyVisitedTime),
            style = MaterialTheme.typography.button,
            color = colorResource(id = R.color.dark_gray)
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(pickUpTimeList) { index, time ->
                TimeChip(
                    index = index,
                    timeText = time,
                    isChecked = index == selectPickUpTimeIndex,
                    setPickUpTime = setPickUpTime
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.buyVisitedTime),
            style = MaterialTheme.typography.button,
            color = colorResource(id = R.color.dark_gray)
        )
        Spacer(Modifier.height(8.dp))
        ExpectedTimeBox(pickUpTime = pickUpTime, pickUpTimeIndex = selectPickUpTimeIndex)
    }
}

@Composable
private fun TimeChip(
    index: Int,
    timeText: String,
    isChecked: Boolean,
    setPickUpTime: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .clickable { setPickUpTime(index) }
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

@SuppressLint("SimpleDateFormat")
@Composable
private fun ExpectedTimeBox(
    pickUpTime: Long,
    pickUpTimeIndex: Int
) {
    val date = Date()
    date.time = pickUpTime * 1000L
    val pickUpDateFormatter = SimpleDateFormat("약 aa hh시 mm분")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = if (pickUpTimeIndex != -1) R.color.pale_yellow else R.color.white))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(4.dp),
                color = colorResource(id = if (pickUpTimeIndex != -1) R.color.light_yellow else R.color.pale_gray)
            )
            .padding(vertical = 10.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = pickUpDateFormatter.format(date),
            style = MaterialTheme.typography.h4
        )
        if (pickUpTimeIndex != -1) Image(
            painter = painterResource(id = R.drawable.icn_check),
            contentDescription = ""
        )
    }
}
