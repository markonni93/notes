package com.thequicknotes.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thequicknotes.finished.FinishedScreen
import com.thequicknotes.home.HomeScreen
import com.thequicknotes.search.SearchScreen
import com.thequicknotes.settings.SettingsScreen

@Composable
fun MainScreenNavigationConfigurations(navController: NavHostController, modifier: Modifier) {
  NavHost(navController, startDestination = MainNavigationScreens.HomeScreen.route) {
    composable(route = MainNavigationScreens.HomeScreen.route) {
      HomeScreen(modifier)
    }
    composable(route = MainNavigationScreens.FinishedScreen.route) {
      FinishedScreen(modifier)
    }
    composable(route = MainNavigationScreens.SearchScreen.route) {
      SearchScreen(modifier)
    }
    composable(route = MainNavigationScreens.SettingsScreen.route) {
      SettingsScreen(modifier)
    }
  }
}