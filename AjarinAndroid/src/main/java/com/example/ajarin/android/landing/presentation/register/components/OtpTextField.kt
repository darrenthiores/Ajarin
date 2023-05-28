package com.example.ajarin.android.landing.presentation.register.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.core.utils.errors.ValidationError

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    error: ValidationError?,
    onOtpTextChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )

                    if (index+1 != otpCount) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    )

    error?.let {
        Text(
            text = it.message ?: "Validation Error",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .size(48.dp)
            .border(
                1.dp,
                if (isFocused) MaterialTheme.colors.primary else Color.LightGray,
                RoundedCornerShape(8.dp)
            )
            .wrapContentHeight(Alignment.CenterVertically),
        text = char,
        style = MaterialTheme.typography.h5,
        color = if (isFocused) {
            MaterialTheme.colors.primary
        } else {
            Color.LightGray
        },
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun OtpTextFieldPreview() {
    AjarinTheme {
        OtpTextField(
            otpText = "",
            onOtpTextChange = {  },
            error = null
        )
    }
}