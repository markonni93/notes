package com.thequicknotes.uicomponents.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.uicomponents.R

@Composable
fun NotesDrawerSheet(onArchiveClicked: () -> Unit, onBinClicked: () -> Unit) {
  ModalDrawerSheet {
    FilledTonalButton(
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp, end = 12.dp, bottom = 6.dp, top = 6.dp),
      onClick = onArchiveClicked,
    ) {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
        Icon(modifier = Modifier.padding(end = 6.dp), painter = painterResource(id = R.drawable.archive_icon), contentDescription = "Archive icon", tint = Color.Unspecified)
        Text(text = "Archive")
      }
    }
    FilledTonalButton(
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp, end = 12.dp, bottom = 6.dp, top = 6.dp),
      onClick = onBinClicked,
    ) {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
        Icon(modifier = Modifier.padding(end = 6.dp), painter = painterResource(id = R.drawable.delete_icon), contentDescription = "Archive icon", tint = Color.Unspecified)
        Text(text = "Bin")
      }
    }
  }
}

@Preview
@Composable
private fun PreviewNotesDrawerSheet() {
  NotesDrawerSheet({}, {})
}