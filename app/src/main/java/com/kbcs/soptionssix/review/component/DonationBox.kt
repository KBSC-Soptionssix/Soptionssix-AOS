package com.kbcs.soptionssix.review.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun DonationBoxes() {
    Row {
        CommonDonationBox(
            contentText = R.string.make_donation,
            contentImage = R.drawable.ic_temp_fire
        )
        Spacer(modifier = Modifier.size(8.dp))
        CommonDonationBox(
            contentText = R.string.get_donation,
            contentImage = R.drawable.ic_temp_fire
        )
    }
}

@Composable
private fun CommonDonationBox(
    @StringRes contentText: Int,
    @DrawableRes contentImage: Int
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
    ) {
        Row {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp),
                text = stringResource(id = contentText)
            )
            Spacer(modifier = Modifier.size(22.dp))
            Image(
                modifier = Modifier
                    .padding(end = 11.dp, top = 11.dp, bottom = 11.dp),
                painter = painterResource(id = contentImage),
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun DonationBoxPreview() {
    DonationBoxes()
}
