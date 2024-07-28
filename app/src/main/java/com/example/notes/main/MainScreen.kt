package com.example.notes.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.notes.main.navigation.MainBottomNavigation
import com.example.notes.main.navigation.MainNavigationScreens
import com.example.notes.main.navigation.MainScreenNavigationConfigurations
import com.example.notes.ui.base.BaseBottomSheetScaffold

@Composable
fun MainScreen() {

  val navController = rememberNavController()

  val bottomNavigationItems = listOf(
    MainNavigationScreens.HomeScreen,
    MainNavigationScreens.FinishedScreen,
    MainNavigationScreens.CreateScreen,
    MainNavigationScreens.SearchScreen,
    MainNavigationScreens.SettingsScreen
  )

  BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), content = {
    Scaffold(bottomBar = {
      BottomAppBar {
        MainBottomNavigation(
          navController,
          bottomNavigationItems
        )
      }
    }) { paddingValues ->
      MainScreenNavigationConfigurations(navController, Modifier.padding(paddingValues))
    }
  })
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    MainScreen()
  }
}