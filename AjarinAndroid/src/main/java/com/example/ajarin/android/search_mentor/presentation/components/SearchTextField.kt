package com.example.ajarin.android.search_mentor.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = {
            Text(text = "Search...")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colors.primary
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                defaultKeyboardAction(ImeAction.Search)
                onSearch()
            }
        ),
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    AjarinTheme {
        SearchTextField(
            text = "",
            onTextChange = {  },
            onSearch = {  }
        )
    }
}