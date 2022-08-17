package com.kbcs.soptionssix.review

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.review.component.DonationBoxes
import com.kbcs.soptionssix.review.component.HorizontalDescriptionBanner
import com.kbcs.soptionssix.review.component.ReviewItem
import com.kbcs.soptionssix.review.component.WriteReviewBox

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
    val focusManager = LocalFocusManager.current
    val isFocus = remember { mutableStateOf(false) }
    val dummyDescriptionList = mutableListOf(
        "플라스틱 포장 용기의 90%는\n" +
            "재활용이 아닌 ㅇㅇㅇㅇㅇ이다",
        "기부 후기를\n" +
            "널리 알리면 왜 좋을까요?",
        "지구를 지키고\n" +
            "나눔을 실천하는 쉬운 방법"
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                    isFocus.value = false
                })
            }
    ) {
        item {
            HorizontalDescriptionBanner(dummyDescriptionList)
            Spacer(modifier = Modifier.size(16.dp))
            DonationBoxes()
            Spacer(modifier = Modifier.size(26.dp))
            WriteReviewBox(isFocus = isFocus)
        }
        items(dummyReviewList) { review ->
            Spacer(modifier = Modifier.size(16.dp))
            ReviewItem(review)
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
