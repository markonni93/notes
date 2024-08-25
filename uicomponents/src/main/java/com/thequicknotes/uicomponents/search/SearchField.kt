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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.uicomponents.R

@Composable
fun SearchField(modifier: Modifier = Modifier, onSearch: (String) -> Unit, onMoreMenuClicked: () -> Unit) {
  var query by rememberSaveable {
    mutableStateOf("")
  }

  val showClearTextIcon by remember {
    derivedStateOf { query.isNotEmpty() }
  }

  val keyboardController = LocalSoftwareKeyboardController.current

  OutlinedTextField(
    modifier = modifier.animateContentSize(),
    value = query,
    onValueChange = { newQuery ->
      query = newQuery
    },
    placeholder = {
      Text(text = "Search notes", style = MaterialTheme.typography.bodyLarge)
    },
    leadingIcon = {
      IconButton(onClick = {
        keyboardController?.hide()
        onMoreMenuClicked()
      }) {
        Icon(painter = painterResource(id = R.drawable.more_menu_icon), contentDescription = "Search icon")
      }
    },
    trailingIcon = {
      AnimatedContent(targetState = showClearTextIcon, label = "clear icon animation") { showIcon ->
        if (showIcon) {
          IconButton(onClick = {
            query = ""
            onSearch(query)
          }) {
            Icon(painter = painterResource(id = R.drawable.remove_icon), contentDescription = "Remove icon")
          }
        }
      }
    },
    textStyle = MaterialTheme.typography.bodyLarge,
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(onSearch = {
      onSearch(query)
    }),
    shape = RoundedCornerShape(28.dp)
  )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchField() {
  SearchField(modifier = Modifier, { query ->
  }, {})
}