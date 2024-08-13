package com.thequicknotes.main.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thequicknotes.uicomponents.theme.theme.AppTheme

@Composable
fun MainBottomNavigation(
  navController: NavHostController,
  bottomNavigationItems: List<MainNavigationScreens>
) {
  val currentRoute = currentRoute(navController)

  NavigationBar {

    bottomNavigationItems.forEach { screen ->
      NavigationBarItem(selected = screen.route == currentRoute, onClick = {
        if (currentRoute != screen.route) navController.navigate(screen.route)
      }, icon = {
        Icon(
          painter = painterResource(id = screen.icon),
          contentDescription = "Home navigation tab",
          tint = if (screen.route == currentRoute) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceBright
        )
      }, label = {
        Text(text = stringResource(id = screen.label))
      })
    }
  }
}


@Composable
private fun currentRoute(navController: NavHostController): String? {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  return navBackStackEntry?.destination?.route
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainBottomNavigation() {
  AppTheme {
    MainBottomNavigation(
      rememberNavController(), listOf(
        MainNavigationScreens.HomeScreen,
        MainNavigationScreens.FinishedScreen,
        MainNavigationScreens.SearchScreen,
        MainNavigationScreens.SettingsScreen
      )
    )
  }
}