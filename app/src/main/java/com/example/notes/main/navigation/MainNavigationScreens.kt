package com.example.notes.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.notes.R

sealed class MainNavigationScreens(val route: String, @StringRes val label: Int, @DrawableRes val icon: Int) {
  data object HomeScreen : MainNavigationScreens(route = "home", label = R.string.navigation_label_home, icon = R.drawable.navigation_icon_home)
  data object FinishedScreen : MainNavigationScreens(route = "finished", label = R.string.navigation_label_finished, icon = R.drawable.navigation_icon_finished)
  data object CreateScreen : MainNavigationScreens(route = "create", label = R.string.navigation_label_create, icon = R.drawable.navigation_icon_search)
  data object SearchScreen : MainNavigationScreens(route = "search", label = R.string.navigation_label_search, icon = R.drawable.navigation_icon_search)
  data object SettingsScreen : MainNavigationScreens(route = "settings", label = R.string.navigation_label_settings, icon = R.drawable.navigation_icon_settings)
}