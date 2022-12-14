package com.kbsc.soptionssix.util.component

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kbsc.soptionssix.R

@Composable
fun DonationBoxes(
    changeScreen: (Int) -> Unit
) {
    Row(
        Modifier.padding(horizontal = 16.dp)
    ) {
        CommonDonationFrame(
            modifier = Modifier
                .clickable { changeScreen(0) }
                .weight(1.0f)
                .padding(end = 4.dp),
            contentText = R.string.reviewMakeDonation,
            descriptionText = R.string.reviewDonationBoxDescription,
            contentImage = R.drawable.ic_tinder,
            stroke = 0.dp,
            textColor = R.color.dark_gray_2,
            descriptionTextColor = R.color.white,
            backgroundColor = R.color.light_brown,
            strokeColor = R.color.light_brown
        )
        CommonDonationFrame(
            modifier = Modifier
                .clickable { changeScreen(1) }
                .weight(1.0f)
                .padding(start = 4.dp),
            contentText = R.string.reviewGetDonation,
            descriptionText = R.string.reviewReviewBoxDescription,
            contentImage = R.drawable.ic_reward,
            stroke = 1.dp,
            textColor = R.color.dark_green,
            descriptionTextColor = R.color.gray,
            backgroundColor = R.color.white,
            strokeColor = R.color.dark_green
        )
    }
}

@Composable
private fun CommonDonationFrame(
    modifier: Modifier,
    stroke: Dp,
    @StringRes contentText: Int,
    @StringRes descriptionText: Int,
    @DrawableRes contentImage: Int,
    @ColorRes textColor: Int,
    @ColorRes descriptionTextColor: Int,
    @ColorRes backgroundColor: Int,
    @ColorRes strokeColor: Int
) {
    Surface(
        modifier = modifier
            .border(
                width = stroke,
                color = colorResource(id = strokeColor),
                shape = RoundedCornerShape(8.dp)
            ),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = backgroundColor))
        ) {
            Row(
                Modifier
                    .height(IntrinsicSize.Min)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Column(modifier = Modifier.padding(top = 22.dp, bottom = 26.dp)) {
                    Text(
                        text = stringResource(id = contentText),
                        color = colorResource(id = textColor),
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = stringResource(id = descriptionText),
                        color = colorResource(id = descriptionTextColor),
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.size(14.dp))
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 16.dp),
                    painter = painterResource(id = contentImage),
                    contentDescription = ""
                )
            }
        }
    }
}
