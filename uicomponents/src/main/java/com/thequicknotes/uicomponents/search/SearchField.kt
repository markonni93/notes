package com.thequicknotes.uicomponents.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.uicomponents.R

@Composable
fun SearchField(modifier: Modifier = Modifier, onSearch: (String) -> Unit, onMoreMenuClicked: () -> Unit) {
  var query by remember {
    mutableStateOf("")
  }

  val showClearTextIcon by remember {
    derivedStateOf { query.isNotEmpty() }
  }

  OutlinedTextField(
    modifier = modifier.animateContentSize(),
    value = query,
    onValueChange = { newQuery ->
      query = newQuery
    },
    placeholder = {
      Text(text = "Search notes", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
    },
    leadingIcon = {
      IconButton(onClick = onMoreMenuClicked) {
        Icon(painter = painterResource(id = R.drawable.more_menu_icon), contentDescription = "Search icon", tint = Color.Unspecified)
      }
    },
    trailingIcon = {
      AnimatedContent(targetState = showClearTextIcon, label = "clear icon animation") { showIcon ->
        if (showIcon) {
          IconButton(onClick = { query = "" }) {
            Icon(painter = painterResource(id = R.drawable.remove_icon), contentDescription = "Remove icon", tint = Color.Unspecified)
          }
        }
      }
    },
    textStyle = MaterialTheme.typography.bodyLarge,
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(onSearch = {
      onSearch(query)
    }),
    shape = RoundedCornerShape(28.dp),
    colors = OutlinedTextFieldDefaults.colors()
      .copy(
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
      )
  )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchField() {
  SearchField(modifier = Modifier, { query ->
  }, {})
}