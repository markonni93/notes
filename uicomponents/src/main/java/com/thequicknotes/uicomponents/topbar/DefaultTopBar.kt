package com.thequicknotes.uicomponents.topbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thequicknotes.uicomponents.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
  @StringRes title: Int? = null,
  @DrawableRes icon: Int = R.drawable.back_icon,
  actions: @Composable (() -> Unit)? = null,
  onNavigationIconClick: () -> Unit
) {
  TopAppBar(title = {
    title?.let {
      Text(text = stringResource(id = title))
    }
  }, navigationIcon = {
    IconButton(onClick = onNavigationIconClick) {
      Icon(painter = painterResource(id = icon), contentDescription = "Back button")
    }
  }, actions = {
    actions?.invoke()
  })
}

@Preview(showBackground = true)
@Composable
private fun PreviewDefaultTopBar() {
  DefaultTopBar(title = R.string.button_delete, icon = R.drawable.back_icon, onNavigationIconClick = {})
}