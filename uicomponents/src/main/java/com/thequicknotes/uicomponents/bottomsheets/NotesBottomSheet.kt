package com.thequicknotes.uicomponents.bottomsheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.uicomponents.R
import com.thequicknotes.uicomponents.bottomsheets.NotesBottomSheetItems.ARCHIVE
import com.thequicknotes.uicomponents.bottomsheets.NotesBottomSheetItems.DELETE
import com.thequicknotes.uicomponents.bottomsheets.NotesBottomSheetItems.SHARE

@Composable
fun NotesBottomSheet(
  onDeleteClicked: () -> Unit,
  onArchiveCLicked: () -> Unit,
  onShareClicked: () -> Unit
) {
  Row(modifier = Modifier.padding(WindowInsets.safeDrawing.asPaddingValues())) {
    NotesBottomSheetItems.entries.forEach { item ->
      val icon = when (item) {
        DELETE -> R.drawable.delete_icon
        ARCHIVE -> R.drawable.archive_icon
        SHARE -> R.drawable.share_icon
      }

      val text = when (item) {
        DELETE -> R.string.button_delete
        ARCHIVE -> R.string.button_archive
        SHARE -> R.string.button_share
      }
      Column(
        modifier = Modifier
          .wrapContentSize()
          .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
          .clickable {
            when (item) {
              DELETE -> onDeleteClicked()
              ARCHIVE -> onArchiveCLicked()
              SHARE -> onShareClicked()
            }
          }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
      ) {
        Icon(modifier = Modifier.size(32.dp), painter = painterResource(id = icon), contentDescription = item.name)
        Text(text = stringResource(id = text), style = MaterialTheme.typography.labelLarge)
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotesBottomSheet() {
  NotesBottomSheet({}, {}, {})
}

enum class NotesBottomSheetItems {
  DELETE, ARCHIVE, SHARE
}