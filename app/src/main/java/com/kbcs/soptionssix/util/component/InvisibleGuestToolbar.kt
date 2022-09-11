package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.kbcs.soptionssix.R

@Composable
fun InvisibleGuestToolBar(
    prefixContent: @Composable () -> Unit = {},
    middleContent: @Composable () -> Unit = {},
    postfixContent: @Composable () -> Unit = { Box {} }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.white)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        prefixContent()
        middleContent()
        postfixContent()
    }
}
