package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun ReviewRegisterButton(
    modifier: Modifier,
    isClickable: Boolean,
    buttonText: String
) {
    Box(
        modifier = modifier
            .background(
                if (isClickable) colorResource(id = R.color.yellow)
                else colorResource(id = R.color.light_gray)
            )
            .clickable(
                enabled = isClickable,
                onClick = { /* to do*/ }
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 16.dp, bottom = 13.dp),
            text = buttonText,
            color = if (isClickable) colorResource(id = R.color.dark_gray_2) else colorResource(id = R.color.white),
            style = MaterialTheme.typography.h3
        )
    }
}
