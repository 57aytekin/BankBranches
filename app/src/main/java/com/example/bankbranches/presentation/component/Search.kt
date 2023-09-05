package com.example.bankbranches.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.bankbranches.ui.theme.BaseGray
import com.example.bankbranches.ui.theme.Blue60
import com.example.bankbranches.ui.theme.Blue70

@Composable
fun SearchTextField(
    searchText: TextFieldValue,
    onSearchFocusChange: (Boolean) -> Unit,
    onSearch: (TextFieldValue) -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier,
    hintText: String,
    bgColor: Color,
) {

    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = modifier
            .background(
                bgColor,
                shape = RoundedCornerShape(16.dp),
        ),
        contentAlignment = Alignment.CenterStart
    ) {
        if (searchText.text.isEmpty()) {
            SearchHint(hintText, Modifier.padding(start = 24.dp, end = 8.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            BasicTextField(
                value = searchText,
                textStyle = TextStyle(
                    color = Color.White
                ),
                cursorBrush = SolidColor(Color.White),
                onValueChange = onSearch,
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged {
                        onSearchFocusChange(it.isFocused)
                    }
                    .focusRequester(focusRequester)
                    .padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 8.dp),
                singleLine = true,
                keyboardActions = KeyboardActions (
                    onSearch = {
                        onSearch.invoke(TextFieldValue(searchText.text))
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                )
            )
            IconButton(onClick = { onSearch(TextFieldValue(searchText.text)) }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = BaseGray
                )
            }
            when {
                searchText.text.isNotEmpty() -> {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .clickable { onClearQuery.invoke() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null,
                            tint = BaseGray,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchHint(text: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .then(modifier)

    ) {
        Text(
            color = BaseGray,
            text = text,
        )
    }
}