package com.example.uicomponents.buttons

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.uicomponents.R

@Composable
fun TertiaryButton(modifier: Modifier = Modifier, @StringRes label: Int, enabled: Boolean, onClick: () -> Unit) {
  TextButton(modifier = modifier, enabled = enabled, onClick = onClick, content = {
    Text(text = stringResource(id = label))
  })
}

@Preview(showBackground = true)
@Composable
fun PreviewTertiaryButton() {
  TertiaryButton(label = R.string.click, enabled = true) {

  }
}