package com.example.notes.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.example.notes.ui.base.BaseBottomSheetScaffold

@Composable
fun HomeScreen(
  onHomeClick: () -> Unit,
  onFinishedClick: () -> Unit,
  onCreateClick: () -> Unit,
  onSearchClick: () -> Unit,
  onSettingsClick: () -> Unit
) {

  BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), content = {
    Scaffold(bottomBar = {
      BottomAppBar {
        HomeBottomNavigation(
          onHomeClick = onHomeClick,
          onFinishedClick = onFinishedClick,
          onCreateClick = onCreateClick,
          onSearchClick = onSearchClick,
          onSettingsClick = onSettingsClick
        )
      }
    }) { paddingValues ->
      Box(modifier = Modifier.padding(paddingValues))
    }
  })
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    HomeScreen({}, {}, {}, {}, {})
  }
}