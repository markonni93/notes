package com.example.notes.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notes.create.CreateScreen
import com.example.notes.finished.FinishedScreen
import com.example.notes.home.HomeScreen
import com.example.notes.search.SearchScreen
import com.example.notes.settings.SettingsScreen

@Composable
fun MainScreenNavigationConfigurations(navController: NavHostController, modifier: Modifier) {
  NavHost(navController, startDestination = MainNavigationScreens.HomeScreen.route) {
    composable(route = MainNavigationScreens.HomeScreen.route) {
      HomeScreen(modifier)
    }
    composable(route = MainNavigationScreens.FinishedScreen.route) {
      FinishedScreen(modifier)
    }
    composable(route = MainNavigationScreens.CreateScreen.route) {
      CreateScreen(modifier)
    }
    composable(route = MainNavigationScreens.SearchScreen.route) {
      SearchScreen(modifier)
    }
    composable(route = MainNavigationScreens.SettingsScreen.route) {
      SettingsScreen(modifier)
    }
  }
}