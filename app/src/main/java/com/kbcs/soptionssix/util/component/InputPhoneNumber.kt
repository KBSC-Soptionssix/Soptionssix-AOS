package com.kbcs.soptionssix.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kbcs.soptionssix.R

@Composable
fun InputPhoneNumber(
    phoneNumber: String,
    writePhoneNumber: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.white))
            .padding(16.dp)
    ) {
        Text(
            text = "전화번호",
            style = MaterialTheme.typography.h1
        )
        Spacer(Modifier.height(8.dp))
        BasicTextField(
            value = phoneNumber,
            onValueChange = { if (it.length < 12) writePhoneNumber(it) },
            textStyle = TextStyle(
                color = colorResource(id = R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    shape = RectangleShape,
                    color = colorResource(id = R.color.pale_gray)
                )
                .padding(vertical = 10.dp, horizontal = 8.dp),
            cursorBrush = SolidColor(colorResource(id = R.color.black)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        ) { innerTextField ->
            if (phoneNumber.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "010-1234-5678",
                    color = colorResource(id = R.color.light_gray),
                    style = MaterialTheme.typography.h4
                )
            }
            innerTextField()
        }
    }
}
