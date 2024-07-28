package com.example.notes.home.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.notes.R

sealed class HomeNavigationScreens(val route: String, @StringRes val label: Int, @DrawableRes val icon: Int) {
  data object HomeScreen : HomeNavigationScreens(route = HOME_SCREEN_ROUTE, label = R.string.navigation_label_home, icon = R.drawable.navigation_icon_home)
  data object FinishedScreen : HomeNavigationScreens(route = FINISHED_SCREEN_ROUTE, label = R.string.navigation_label_finished, icon = R.drawable.navigation_icon_finished)
  data object CreateScreen : HomeNavigationScreens(route = CREATE_SCREEN_ROUTE, label = R.string.navigation_label_create, icon = R.drawable.navigation_icon_search)
  data object SearchScreen : HomeNavigationScreens(route = SEARCH_SCREEN_ROUTE, label = R.string.navigation_label_search, icon = R.drawable.navigation_icon_search)
  data object SettingsScreen : HomeNavigationScreens(route = SETTINGS_SCREEN_ROUTE, label = R.string.navigation_label_settings, icon = R.drawable.navigation_icon_settings)

  companion object {
    const val HOME_SCREEN_ROUTE = "home"
    const val FINISHED_SCREEN_ROUTE = "finished"
    const val CREATE_SCREEN_ROUTE = "create"
    const val SEARCH_SCREEN_ROUTE = "search"
    const val SETTINGS_SCREEN_ROUTE = "settings"
  }
}