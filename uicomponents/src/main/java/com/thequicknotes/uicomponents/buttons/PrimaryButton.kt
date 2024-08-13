package com.thequicknotes.uicomponents.buttons

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thequicknotes.uicomponents.R

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, @StringRes label: Int, enabled: Boolean, onClick: () -> Unit) {
  Button(modifier = modifier, enabled = enabled, onClick = onClick, content = {
    Text(text = stringResource(id = label))
  })
}

@Preview(showBackground = true)
@Composable
fun PreviewPrimaryButton() {
  PrimaryButton(label = R.string.click, enabled = true) {

  }
}