package com.thequicknotes.finished

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thequicknotes.R

@Composable
fun EmptyFinishedScreen(modifier: Modifier) {
  Column(modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
    Icon(
      modifier = Modifier
        .padding(start = 60.dp, end = 60.dp, top = 110.dp),
      painter = painterResource(id = R.drawable.finished_empty_screen),
      contentDescription = "No notes placeholder",
      tint = Color.Unspecified
    )

    Text(modifier = Modifier.padding(top = 24.dp), text = stringResource(id = R.string.finished_screen_no_notes_title), style = MaterialTheme.typography.titleMedium)
    Text(
      modifier = Modifier.padding(top = 16.dp, start = 60.dp, end = 60.dp),
      text = stringResource(id = R.string.finished_screen_no_notes_description),
      style = MaterialTheme.typography.bodySmall,
      textAlign = TextAlign.Center
    )
  }
}