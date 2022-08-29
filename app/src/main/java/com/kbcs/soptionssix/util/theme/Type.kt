package com.kbcs.soptionssix.util.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kbcs.soptionssix.R

private val Pretendard = FontFamily(
    Font(R.font.font_pretendard_bold, FontWeight.W700, FontStyle(0)),
    Font(R.font.font_pretendard_regular, FontWeight.W400, FontStyle(0))
)

private val PretendardMedium = FontFamily(
    Font(R.font.font_pretendard_medium, FontWeight.W500, FontStyle(0))
)

private val PretendardSemiBold = FontFamily(
    Font(R.font.font_pretendard_semi_bold, FontWeight.W600, FontStyle(0))
)

val PretendardTypography = Typography(
    h1 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    h2 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    h3 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),
    body2 = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 6.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 3.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 20.sp
    ),
    caption = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 7.sp
    ),
    overline = TextStyle(
        fontFamily = PretendardSemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp
    )
)
