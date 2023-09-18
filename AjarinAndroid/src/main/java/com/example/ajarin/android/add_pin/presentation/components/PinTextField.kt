package com.example.ajarin.android.add_pin.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.example.ajarin.domain.validation.utils.ValidationError

@Composable
fun PinTextField(
    modifier: Modifier = Modifier,
    pinText: String,
    pinCount: Int = 6,
    error: ValidationError?,
    onPinChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = pinText,
        onValueChange = {
            if (it.length <= pinCount) {
                onPinChange.invoke(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(pinCount) { index ->
                    CharView(
                        index = index,
                        text = pinText
                    )

                    if (index+1 != pinCount) {
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
fun PinTextFieldPreview() {
    AjarinTheme {
        PinTextField(
            pinText = "",
            onPinChange = {  },
            error = null
        )
    }
}