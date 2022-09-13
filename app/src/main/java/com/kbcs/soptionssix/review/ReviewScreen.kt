package com.kbcs.soptionssix.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.util.component.DonationBoxes
import com.kbcs.soptionssix.util.component.HorizontalDescriptionBanner
import com.kbcs.soptionssix.util.component.InvisibleGuestToolBar
import com.kbcs.soptionssix.util.component.ReviewItem
import com.kbcs.soptionssix.util.theme.PretendardTypography

@Composable
fun ReviewScreen(
    reviewViewModel: ReviewViewModel,
    changeScreen: (Int) -> Unit,
    goStoreDetail: (String) -> Unit
) {
    val dummyReviewList = reviewViewModel.reviewList.collectAsState()
    val dummyDescriptionList = listOf(
        "지구를 지키고,\n나눔을 실천하는 쉬운 방법",
        "기부 후기를 널리 알리는 게\n유행하는 이유",
        "일회용 포장 용기 종류별\n재활용 방법"
    )
    MaterialTheme(typography = PretendardTypography) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                ReviewToolBar()
                HorizontalDescriptionBanner(dummyDescriptionList)
                Spacer(modifier = Modifier.size(16.dp))
                DonationBoxes(changeScreen = changeScreen)
                Spacer(modifier = Modifier.size(24.dp))
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(colorResource(id = R.color.view_background))
                )
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.reviewReviewListTitle),
                    style = MaterialTheme.typography.h1
                )
            }
            items(dummyReviewList.value) { review ->
                Spacer(modifier = Modifier.size(24.dp))
                ReviewItem(
                    review = review,
                    goStoreDetail = goStoreDetail
                )
            }
            item { Spacer(modifier = Modifier.size(24.dp)) }
        }
    }
}

@Composable
fun ReviewToolBar() {
    InvisibleGuestToolBar(
        prefixContent = {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 24.dp),
                text = "서울특별시 중구 명동",
                style = MaterialTheme.typography.h1
            )
        }
    )
}
