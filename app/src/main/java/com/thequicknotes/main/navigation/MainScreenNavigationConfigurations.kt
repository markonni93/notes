package com.thequicknotes.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thequicknotes.archive.ArchiveScreen
import com.thequicknotes.home.HomeScreen
import com.thequicknotes.settings.SettingsScreen

@Composable
fun MainScreenNavigationConfigurations(navController: NavHostController, modifier: Modifier, showBottomSheet: (MainScreenBottomSheetConfiguration) -> Unit) {
  NavHost(navController, startDestination = MainNavigationScreens.HomeScreen.route) {
    composable(route = MainNavigationScreens.HomeScreen.route) {
      HomeScreen(modifier, showBottomSheet)
    }
    composable(route = MainNavigationScreens.ArchiveScreen.route) {
      ArchiveScreen(modifier)
    }
    composable(route = MainNavigationScreens.SettingsScreen.route) {
      SettingsScreen(modifier)
    }
  }
}

sealed class MainScreenBottomSheetConfiguration {
  data class HomeScreenBottomSheetConfiguration(val id: Int) : MainScreenBottomSheetConfiguration()
}