package com.thequicknotes.uicomponents.drawer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.uicomponents.R
import com.thequicknotes.uicomponents.drawer.NotesDrawerItem.ARCHIVE
import com.thequicknotes.uicomponents.drawer.NotesDrawerItem.BIN
import com.thequicknotes.uicomponents.drawer.NotesDrawerItem.HOME
import com.thequicknotes.uicomponents.drawer.NotesDrawerItem.SETTINGS

@Composable
fun NotesDrawerSheet(onNavItemClicked: (NotesDrawerItem) -> Unit) {
  ModalDrawerSheet(
    modifier = Modifier.fillMaxWidth(0.7f),
    drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
  ) {

    val selectedItem by rememberSaveable {
      mutableStateOf(HOME)
    }

    Text(modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp), text = "Quick Notes", style = MaterialTheme.typography.headlineLarge)
    NotesDrawerItem.entries.forEach { item ->
      NavigationDrawerItem(modifier = Modifier.padding(horizontal = 8.dp), selected = item == selectedItem, onClick = {
        onNavItemClicked(item)
      }, label = {
        val text = when (item) {
          HOME -> R.string.home_nav_rail_item
          BIN -> R.string.bin_nav_rail_item
          ARCHIVE -> R.string.archive_nav_rail_item
          // SECRET -> R.string.secret_nav_rail_item
          SETTINGS -> R.string.settings_nav_rail_item
        }

        Text(text = stringResource(id = text), style = MaterialTheme.typography.labelMedium)
      }, icon = {
        val icon = when (item) {
          HOME -> R.drawable.home_icon
          BIN -> R.drawable.delete_icon
          ARCHIVE -> R.drawable.archive_icon
          //  SECRET -> R.drawable.lock_icon
          SETTINGS -> R.drawable.settings_icon
        }
        Icon(painter = painterResource(id = icon), contentDescription = "Archive icon")
      })
    }
  }
}

@Preview
@Composable
private fun PreviewNotesDrawerSheet() {
  NotesDrawerSheet({})
}

enum class NotesDrawerItem {
  HOME,
  BIN,
  ARCHIVE,

  // TODO Enable once the screen is ready
  // SECRET,
  SETTINGS
}