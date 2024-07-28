package com.example.notes.home

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notes.R
import com.example.notes.home.HomeNavigationItem.CREATE
import com.example.notes.home.HomeNavigationItem.FINISHED
import com.example.notes.home.HomeNavigationItem.HOME
import com.example.notes.home.HomeNavigationItem.SEARCH
import com.example.notes.home.HomeNavigationItem.SETTINGS

@Composable
fun HomeBottomNavigation(
  onHomeClick: () -> Unit,
  onFinishedClick: () -> Unit,
  onCreateClick: () -> Unit,
  onSearchClick: () -> Unit,
  onSettingsClick: () -> Unit
) {
  var selectedItem by remember { mutableStateOf(HOME) }

  NavigationBar {
    HomeNavigationItem.entries.forEach { item ->
      when (item) {
        HOME -> NavigationBarItem(selected = item == selectedItem, onClick = {
          onHomeClick.invoke()
          selectedItem = item
        }, icon = {
          Icon(
            painter = painterResource(id = R.drawable.navigation_icon_home),
            contentDescription = "Home navigation tab",
            tint = if (item == selectedItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceBright
          )
        }, label = {
          Text(text = stringResource(id = R.string.navigation_label_home))
        })

        FINISHED -> NavigationBarItem(selected = item == selectedItem, onClick = {
          onFinishedClick.invoke()
          selectedItem = item
        }, icon = {
          Icon(
            painter = painterResource(id = R.drawable.navigation_icon_finished),
            contentDescription = "Finished navigation tab",
            tint = if (item == selectedItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceBright
          )
        }, label = {
          Text(text = stringResource(id = R.string.navigation_label_finished))
        })

        CREATE -> NavigationBarItem(selected = item == selectedItem, onClick = {
          onCreateClick.invoke()
          selectedItem = item
        }, icon = {
          Icon(
            painter = painterResource(id = R.drawable.navigation_icon_create),
            contentDescription = "Create navigation tab",
            tint = if (item == selectedItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceBright
          )
        }, label = {
          Text(text = stringResource(id = R.string.navigation_label_create))
        })

        SEARCH -> NavigationBarItem(selected = item == selectedItem, onClick = {
          onSearchClick.invoke()
          selectedItem = item
        }, icon = {
          Icon(
            painter = painterResource(id = R.drawable.navigation_icon_search),
            contentDescription = "Search navigation tab",
            tint = if (item == selectedItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceBright
          )
        }, label = {
          Text(text = stringResource(id = R.string.navigation_label_search))
        })

        SETTINGS -> NavigationBarItem(selected = item == selectedItem, onClick = {
          onSettingsClick.invoke()
          selectedItem = item
        }, icon = {
          Icon(
            painter = painterResource(id = R.drawable.navigation_icon_settings),
            contentDescription = "Home navigation tab",
            tint = if (item == selectedItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceBright
          )
        }, label = {
          Text(text = stringResource(id = R.string.navigation_label_settings))
        })
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeBottomNavigation() {
  MaterialTheme {
    HomeBottomNavigation({}, {}, {}, {}, {})
  }
}