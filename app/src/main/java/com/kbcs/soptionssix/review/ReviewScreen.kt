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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.review.component.DonationBoxes
import com.kbcs.soptionssix.review.component.HorizontalDescriptionBanner
import com.kbcs.soptionssix.review.component.ReviewItem
import com.kbcs.soptionssix.review.theme.PretendardTypography

@Composable
fun ReviewScreen() {
    val dummyReviewList = mutableListOf(
        Review(
            title = "맑은비",
            time = "5분 전",
            address = "백현동",
            content = "첫번째 방문에 너무 득템했는데\n" +
                "오늘 두번쨰 방문인데 후기를 안남길 수가 없어요\n" +
                "참깨, 무화과, 갈릭바게트 베이글 전부 다 존맛이고 크림치즈 서비스스스스스스\n" +
                "이거까지 보이나요???",
            storeName = "몽실 베이커리"
        ),
        Review(
            title = "맑은비",
            time = "5분 전",
            address = "백현동",
            content = "첫번째 방문에 너무 득템했는데\n" +
                "오늘 두번쨰 방문인데 후기를 안남길 수가 없어요\n" +
                "참깨, 무화과, 갈릭바게트 베이글 전부 다 존맛이고 크림치즈 서비스스스스스스\n" +
                "이거까지 보이나요???",
            storeName = "몽실 베이커리"
        ),
        Review(
            title = "맑은비",
            time = "5분 전",
            address = "백현동",
            content = "첫번째 방문에 너무 득템했는데\n" +
                "오늘 두번쨰 방문인데 후기를 안남길 수가 없어요\n" +
                "참깨, 무화과, 갈릭바게트 베이글 전부 다 존맛이고 크림치즈 서비스스스스스스\n" +
                "이거까지 보이나요???",
            storeName = "몽실 베이커리"
        )
    )
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
                HorizontalDescriptionBanner(dummyDescriptionList)
                Spacer(modifier = Modifier.size(16.dp))
                DonationBoxes()
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
            items(dummyReviewList) { review ->
                Spacer(modifier = Modifier.size(24.dp))
                ReviewItem(review)
            }
        }
    }
}

data class Review(
    val title: String,
    val time: String,
    val address: String,
    val content: String,
    val storeName: String,
    val reviewImages: List<String> = emptyList()
)

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    ReviewScreen()
}
