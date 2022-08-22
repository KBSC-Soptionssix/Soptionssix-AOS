package com.kbcs.soptionssix.review.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kbcs.soptionssix.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalDescriptionBanner(
    descriptionList: List<String>
) {
    val state = rememberPagerState()
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(3000)
            state.animateScrollToPage(
                page = (state.currentPage + 1) % (state.pageCount)
            )
        }
    }
    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        state = state,
        count = descriptionList.size,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { page ->
        Surface(
            modifier = Modifier.padding(horizontal = 6.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box {
                DonationDescriptionItem(
                    descriptionList[page],
                    page
                )
            }
        }
    }
}

@Composable
private fun DonationDescriptionItem(
    description: String,
    page: Int
) {
    val backgroundColor = when (page) {
        0 -> R.color.dark_green
        1 -> R.color.orange
        else -> R.color.blue_banner
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = backgroundColor))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 14.dp, top = 15.dp, bottom = 15.dp),
                text = description,
                style = MaterialTheme.typography.h2,
                color = colorResource(id = R.color.white)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.padding(top = 6.dp),
                    painter = painterResource(id = R.drawable.ic_together),
                    contentDescription = ""
                )
                Spacer(Modifier.size(24.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_temp_right),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview
@Composable
fun DonationDescriptionBoxPreview() {
    DonationDescriptionItem(stringResource(id = R.string.review_donation_description), 1)
}
