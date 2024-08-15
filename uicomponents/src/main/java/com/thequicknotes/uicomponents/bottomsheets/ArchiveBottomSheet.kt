package com.thequicknotes.uicomponents.bottomsheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.uicomponents.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveBottomSheet(id: Int, state: SheetState, onDismissRequest: () -> Unit, onDeleteClicked: (Int) -> Unit, onArchiveClicked: (Int) -> Unit) {

  ModalBottomSheet(modifier = Modifier
    .fillMaxSize(), sheetState = state, onDismissRequest = { onDismissRequest() }) {

    Row(modifier = Modifier
      .fillMaxWidth()
      .height(48.dp)
      .align(Alignment.Start)
      .padding(horizontal = 12.dp)
      .clickable {
        // Todo
      }) {
      Icon(
        modifier = Modifier
          .size(32.dp)
          .align(Alignment.CenterVertically),
        painter = painterResource(id = R.drawable.share_icon),
        contentDescription = "Archive icon",
        tint = Color.Unspecified
      )
      Text(
        modifier = Modifier
          .align(Alignment.CenterVertically)
          .padding(horizontal = 12.dp),
        text = stringResource(id = R.string.button_share),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
      )
    }

    Row(modifier = Modifier
      .height(48.dp)
      .fillMaxWidth()
      .align(Alignment.Start)
      .padding(horizontal = 12.dp)
      .clickable {
        onArchiveClicked(id)
      }) {
      Icon(
        modifier = Modifier
          .size(32.dp)
          .align(Alignment.CenterVertically),
        painter = painterResource(id = R.drawable.archive_icon),
        contentDescription = "Archive icon",
        tint = Color.Unspecified
      )
      Text(
        modifier = Modifier
          .align(Alignment.CenterVertically)
          .padding(horizontal = 12.dp),
        text = stringResource(id = R.string.button_archive),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
      )
    }

    Row(modifier = Modifier
      .height(48.dp)
      .fillMaxWidth()
      .align(Alignment.Start)
      .padding(horizontal = 12.dp)
      .clickable {
        onDeleteClicked(id)
      }) {
      Icon(
        modifier = Modifier
          .size(32.dp)
          .align(Alignment.CenterVertically),
        painter = painterResource(id = R.drawable.delete_icon),
        contentDescription = "Delete icon",
        tint = Color.Unspecified
      )
      Text(
        modifier = Modifier
          .align(Alignment.CenterVertically)
          .padding(horizontal = 12.dp),
        text = stringResource(id = R.string.button_delete),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.error
      )
    }

  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewArchiveBottomSheet() {
  ArchiveBottomSheet(id = 1, state = rememberModalBottomSheetState(skipPartiallyExpanded = false), onDismissRequest = { }, onDeleteClicked = {}, onArchiveClicked = {})
}