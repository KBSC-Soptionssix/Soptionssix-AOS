package com.kbcs.soptionssix.review.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun DonationBoxes() {
    Row(
        Modifier.padding(horizontal = 16.dp)
    ) {
        CommonDonationBox(
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 4.dp),
            contentText = R.string.make_donation,
            contentImage = R.drawable.ic_temp_fire
        )
        CommonDonationBox(
            modifier = Modifier
                .weight(1.0f)
                .padding(start = 4.dp),
            contentText = R.string.get_donation,
            contentImage = R.drawable.ic_temp_fire
        )
    }
}

@Composable
private fun CommonDonationBox(
    modifier: Modifier,
    @StringRes contentText: Int,
    @DrawableRes contentImage: Int
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 12.dp),
                text = stringResource(id = contentText)
            )
            Spacer(modifier = Modifier.size(14.dp))
            Image(
                modifier = Modifier.padding(vertical = 12.dp),
                painter = painterResource(id = contentImage),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DonationBoxPreview() {
    Box(Modifier.background(Color.Gray)) {
        DonationBoxes()
    }
}