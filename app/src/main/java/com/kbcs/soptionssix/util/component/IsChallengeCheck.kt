package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
fun IsChallengeCheck(
    isChecked: Boolean,
    setIsChecked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "용기내챌린지 참여 여부 ",
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(colorResource(id = R.color.light_green))
                    .padding(3.dp),
                text = "무슨 챌린지인가요?",
                style = MaterialTheme.typography.overline,
                color = colorResource(id = R.color.dark_green)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        CheckText(
            isChecked = isChecked,
            contentText = "일회용품 포장 대신 직접 포장용기를 가져갈게요!",
            setIsChecked = setIsChecked
        )
        Spacer(modifier = Modifier.height(16.dp))
        CheckText(
            isChecked = !isChecked,
            contentText = "다음에 참여할게요.",
            setIsChecked = setIsChecked
        )
    }
}

@Composable
private fun CheckText(
    isChecked: Boolean,
    contentText: String,
    setIsChecked: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(21.dp)
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = if (isChecked) colorResource(id = R.color.dark_green)
                    else colorResource(id = R.color.pale_gray)
                )
                .background(
                    if (isChecked) colorResource(id = R.color.dark_green)
                    else colorResource(id = R.color.white)
                )
                .clickable { setIsChecked() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = if (isChecked) R.drawable.group_130 else R.drawable.group_131
                ),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = contentText,
            style = MaterialTheme.typography.h4,
            color = if (isChecked) colorResource(id = R.color.black) else colorResource(id = R.color.gray)
        )
    }
}
