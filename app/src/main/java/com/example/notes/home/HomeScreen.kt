package com.example.notes.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.notes.home.navigation.HomeBottomNavigation
import com.example.notes.home.navigation.HomeNavigationScreens
import com.example.notes.ui.base.BaseBottomSheetScaffold

@Composable
fun HomeScreen() {

  val navController = rememberNavController()
  val bottomNavigationItems = listOf(
    HomeNavigationScreens.HomeScreen,
    HomeNavigationScreens.FinishedScreen,
    HomeNavigationScreens.CreateScreen,
    HomeNavigationScreens.SearchScreen,
    HomeNavigationScreens.SettingsScreen
  )

  BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), content = {
    Scaffold(bottomBar = {
      BottomAppBar {
        HomeBottomNavigation(
          navController,
          bottomNavigationItems
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
    HomeScreen()
  }
}